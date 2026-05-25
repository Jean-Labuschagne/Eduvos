"""Service layer for waste submission operations."""

from __future__ import annotations

from typing import Tuple

from domain.household import Household
from domain.waste_item import WasteItem
from domain.waste_type import WasteType
from exceptions.custom_exceptions import ValidationException
from services.household_service import HouseholdService
from services.metrics_service import MetricsService
from utils.validation import (
    validate_positive_weight,
    validate_required_fields,
    validate_waste_type,
)


class WasteService:
    """Coordinates waste validation, creation, and household assignment."""

    def __init__(self, household_service: HouseholdService) -> None:
        self._household_service = household_service

    def submit_waste(
        self,
        household_id: str,
        item_name: str,
        waste_type_input: str,
        weight_kg: float,
    ) -> WasteItem:
        """Validate and submit waste for a household."""
        waste_type, valid_weight = self.validate_input(
            household_id=household_id,
            item_name=item_name,
            waste_type_input=waste_type_input,
            weight_kg=weight_kg,
        )

        waste_item = WasteItem(item_name=item_name, waste_type=waste_type, weight_kg=valid_weight)
        self.assign_to_household(household_id, waste_item)
        MetricsService.track_waste(waste_type, valid_weight)
        return waste_item

    def validate_input(
        self,
        household_id: str,
        item_name: str,
        waste_type_input: str,
        weight_kg: float,
    ) -> Tuple[WasteType, float]:
        """Validate waste submission data and return cleaned values."""
        validate_required_fields(
            {
                "household_id": household_id,
                "item_name": item_name,
                "waste_type": waste_type_input,
            }
        )

        waste_type = validate_waste_type(waste_type_input)
        valid_weight = validate_positive_weight(weight_kg)
        return waste_type, valid_weight

    def assign_to_household(self, household_id: str, waste_item: WasteItem) -> Household:
        """Attach waste item to a household and return the household."""
        household = self._household_service.get_household(household_id)
        if household is None:
            raise ValidationException(f"Household with ID '{household_id}' was not found.")

        household.add_waste(waste_item)
        return household
