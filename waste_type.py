"""Waste type enum used in the domain."""

from enum import Enum


class WasteType(Enum):
    """Supported waste categories."""

    PLASTIC = "Plastic"
    ORGANIC = "Organic"
    HAZARDOUS = "Hazardous"
