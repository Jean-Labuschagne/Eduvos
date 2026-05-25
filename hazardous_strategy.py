"""Strategy for hazardous waste impact calculation."""

from strategies.waste_impact_strategy import WasteImpactStrategy


class HazardousStrategy(WasteImpactStrategy):
    """Hazardous waste multiplier strategy."""

    MULTIPLIER = 6

    def calculate(self, weight: float) -> float:
        return weight * self.MULTIPLIER
