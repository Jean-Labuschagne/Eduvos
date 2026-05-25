"""Metrics service for global tracking values."""

from __future__ import annotations

from typing import Dict

from domain.waste_type import WasteType


class MetricsService:
    """Tracks aggregate metrics using class-level storage."""

    total_households: int = 0
    waste_totals_by_type: Dict[WasteType, float] = {
        WasteType.PLASTIC: 0.0,
        WasteType.ORGANIC: 0.0,
        WasteType.HAZARDOUS: 0.0,
    }

    @classmethod
    def track_household_registered(cls) -> None:
        """Increase total household count."""
        cls.total_households += 1

    @classmethod
    def track_waste(cls, waste_type: WasteType, weight_kg: float) -> None:
        """Increase tracked weight for a waste category."""
        cls.waste_totals_by_type[waste_type] += weight_kg

    @classmethod
    def get_total_households(cls) -> int:
        """Return total registered households."""
        return cls.total_households

    @classmethod
    def get_waste_totals(cls) -> Dict[WasteType, float]:
        """Return waste totals by category."""
        return dict(cls.waste_totals_by_type)
