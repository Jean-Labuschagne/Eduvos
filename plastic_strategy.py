"""Strategy for plastic waste impact calculation."""

from strategies.waste_impact_strategy import WasteImpactStrategy


class PlasticStrategy(WasteImpactStrategy):
    """Plastic waste multiplier strategy."""

    MULTIPLIER = 2

    def calculate(self, weight: float) -> float:
        return weight * self.MULTIPLIER
