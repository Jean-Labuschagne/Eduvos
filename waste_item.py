"""Waste item entity model."""

from __future__ import annotations

from dataclasses import dataclass, field

from domain.waste_type import WasteType
from strategies.strategy_map import get_strategy


@dataclass
class WasteItem:
    """Represents a submitted waste item with calculated eco score."""

    item_name: str
    waste_type: WasteType
    weight_kg: float
    eco_score: float = field(init=False)

    def __post_init__(self) -> None:
        strategy = get_strategy(self.waste_type)
        self.eco_score = strategy.calculate(self.weight_kg)
