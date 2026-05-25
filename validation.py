"""Validation helper functions used by service classes."""

from __future__ import annotations

from typing import Dict

from domain.waste_type import WasteType
from exceptions.custom_exceptions import (
    InvalidWasteTypeException,
    InvalidWeightException,
    ValidationException,
)


def validate_required_fields(fields: Dict[str, str]) -> None:
    """Validate that all required fields contain non-empty values."""
    missing = [name for name, value in fields.items() if not str(value).strip()]
    if missing:
        raise ValidationException(f"Missing required fields: {', '.join(missing)}")


def validate_positive_weight(weight: float) -> float:
    """Validate weight and return it as a float."""
    if weight <= 0:
        raise InvalidWeightException("Weight must be greater than 0 kg.")
    return weight


def validate_waste_type(waste_type: str) -> WasteType:
    """Convert user input text to a valid WasteType enum value."""
    try:
        normalized = waste_type.strip().upper()
        return WasteType[normalized]
    except KeyError as exc:
        allowed = ", ".join(item.name for item in WasteType)
        raise InvalidWasteTypeException(
            f"Unsupported waste type '{waste_type}'. Allowed types: {allowed}."
        ) from exc
