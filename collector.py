"""Collector entity model."""

from dataclasses import dataclass


@dataclass
class Collector:
    """Represents a waste collector assigned to a service area."""

    collector_id: str
    name: str
    service_area: str
