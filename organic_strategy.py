"""Strategy for organic waste impact calculation."""

from strategies.waste_impact_strategy import WasteImpactStrategy


class OrganicStrategy(WasteImpactStrategy):
    """Organic waste multiplier strategy."""

    MULTIPLIER = 1

    def calculate(self, weight: float) -> float:
        return weight * self.MULTIPLIER
