"""Service layer for household operations."""

from __future__ import annotations

from typing import Dict, List, Optional

from domain.household import Household
from services.metrics_service import MetricsService
from utils.validation import validate_required_fields


class HouseholdService:
    """Handles household registration and retrieval operations."""

    def __init__(self) -> None:
        self._households: Dict[str, Household] = {}

    def register_household(
        self,
        household_id: str,
        head_name: str,
        contact_number: str,
        address: str,
    ) -> Household:
        """Register and return a new household record."""
        validate_required_fields(
            {
                "household_id": household_id,
                "head_name": head_name,
                "contact_number": contact_number,
                "address": address,
            }
        )

        household = Household(
            household_id=household_id,
            head_name=head_name,
            contact_number=contact_number,
            address=address,
        )
        self._households[household_id] = household
        MetricsService.track_household_registered()
        return household

    def get_household(self, household_id: str) -> Optional[Household]:
        """Get a household by its ID or None if not found."""
        return self._households.get(household_id)

    def list_households(self) -> List[Household]:
        """Return all registered households."""
        return list(self._households.values())
