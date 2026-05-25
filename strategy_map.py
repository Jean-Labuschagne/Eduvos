"""Dictionary-based strategy mapping for waste type handling."""

from domain.waste_type import WasteType
from strategies.hazardous_strategy import HazardousStrategy
from strategies.organic_strategy import OrganicStrategy
from strategies.plastic_strategy import PlasticStrategy
from strategies.waste_impact_strategy import WasteImpactStrategy

STRATEGY_MAP = {
    WasteType.PLASTIC: PlasticStrategy(),
    WasteType.ORGANIC: OrganicStrategy(),
    WasteType.HAZARDOUS: HazardousStrategy(),
}


def get_strategy(waste_type: WasteType) -> WasteImpactStrategy:
    """Return strategy instance for the given waste type."""
    return STRATEGY_MAP[waste_type]
