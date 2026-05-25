"""Household entity model."""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import List

from domain.waste_item import WasteItem


@dataclass
class Household:
    """Represents a registered household."""

    household_id: str
    head_name: str
    contact_number: str
    address: str
    waste_submissions: List[WasteItem] = field(default_factory=list)

    def add_waste(self, waste_item: WasteItem) -> None:
        """Attach a waste submission to this household."""
        self.waste_submissions.append(waste_item)

    def calculate_total_eco_score(self) -> float:
        """Return the cumulative eco impact score for the household."""
        return sum(item.eco_score for item in self.waste_submissions)
