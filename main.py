"""Entry point for the GreenTrack Waste Management Tracking System."""

from __future__ import annotations

from exceptions.custom_exceptions import ValidationException
from services.collector_service import CollectorService
from services.household_service import HouseholdService
from services.metrics_service import MetricsService
from services.waste_service import WasteService
from utils import format_output, kg_to_tons


class GreenTrackApp:
    """Console application controller for menu-driven interaction."""

    def __init__(self) -> None:
        self.household_service = HouseholdService()
        self.collector_service = CollectorService()
        self.waste_service = WasteService(self.household_service)

    def run(self) -> None:
        """Run the main menu loop."""
        while True:
            self._show_menu()
            choice = input("Select an option: ").strip()

            try:
                if choice == "1":
                    self._register_household()
                elif choice == "2":
                    self._register_collector()
                elif choice == "3":
                    self._submit_waste()
                elif choice == "4":
                    self._view_submissions()
                elif choice == "5":
                    self._show_eco_impact_per_household()
                elif choice == "6":
                    print("Exiting GreenTrack. Goodbye.")
                    break
                else:
                    print("Invalid option. Please choose a number from 1 to 6.")
            except ValidationException as exc:
                print(f"Input error: {exc}")
            except ValueError:
                print("Input error: Weight must be a numeric value.")
            except Exception as exc:  # Defensive fallback for unexpected runtime errors.
                print(f"Unexpected error: {exc}")

    @staticmethod
    def _show_menu() -> None:
        print(
            "\nGreenTrack - Waste Management Tracking System\n"
            "1. Register Household\n"
            "2. Register Collector\n"
            "3. Submit Waste\n"
            "4. View Submissions\n"
            "5. Show Eco Impact Per Household\n"
            "6. Exit"
        )

    def _register_household(self) -> None:
        household = self.household_service.register_household(
            household_id=input("Household ID: ").strip(),
            head_name=input("Head Name: ").strip(),
            contact_number=input("Contact Number: ").strip(),
            address=input("Address: ").strip(),
        )
        print(f"Household '{household.household_id}' registered successfully.")

    def _register_collector(self) -> None:
        collector = self.collector_service.register_collector(
            collector_id=input("Collector ID: ").strip(),
            name=input("Collector Name: ").strip(),
            service_area=input("Service Area: ").strip(),
        )
        print(f"Collector '{collector.collector_id}' registered successfully.")

    def _submit_waste(self) -> None:
        waste_item = self.waste_service.submit_waste(
            household_id=input("Household ID: ").strip(),
            item_name=input("Item Name: ").strip(),
            waste_type_input=input("Waste Type (PLASTIC/ORGANIC/HAZARDOUS): ").strip(),
            weight_kg=float(input("Weight (kg): ").strip()),
        )
        print(
            f"Waste submitted. Eco score for '{waste_item.item_name}' is "
            f"{waste_item.eco_score:.2f}."
        )

    def _view_submissions(self) -> None:
        households = self.household_service.list_households()
        if not households:
            print("No households registered yet.")
            return

        output_lines = []
        for household in households:
            output_lines.append(
                f"Household {household.household_id} - {household.head_name} ({household.address})"
            )
            if not household.waste_submissions:
                output_lines.append("  No submissions yet.")
                continue

            for item in household.waste_submissions:
                output_lines.append(
                    f"  - {item.item_name}: {item.waste_type.name}, {item.weight_kg:.2f} kg, "
                    f"Eco Score {item.eco_score:.2f}"
                )

        print(format_output("Waste Submissions", "\n".join(output_lines)))

    def _show_eco_impact_per_household(self) -> None:
        households = self.household_service.list_households()
        if not households:
            print("No households registered yet.")
            return

        output_lines = []
        for household in households:
            total_weight_kg = sum(item.weight_kg for item in household.waste_submissions)
            output_lines.append(
                f"Household {household.household_id}: "
                f"Eco Score {household.calculate_total_eco_score():.2f}, "
                f"Total Waste {total_weight_kg:.2f} kg ({kg_to_tons(total_weight_kg):.4f} tons)"
            )

        output_lines.append(f"Total Registered Households: {MetricsService.get_total_households()}")
        waste_totals = MetricsService.get_waste_totals()
        output_lines.append(
            "Waste Totals by Type: "
            + ", ".join(f"{waste_type.name}={weight:.2f} kg" for waste_type, weight in waste_totals.items())
        )

        print(format_output("Eco Impact Summary", "\n".join(output_lines)))


if __name__ == "__main__":
    app = GreenTrackApp()
    app.run()
