"""Abstract strategy contract for eco score calculation."""

from abc import ABC, abstractmethod


class WasteImpactStrategy(ABC):
    """Base strategy for calculating eco impact from weight."""

    @abstractmethod
    def calculate(self, weight: float) -> float:
        """Return eco impact score for the given weight."""
