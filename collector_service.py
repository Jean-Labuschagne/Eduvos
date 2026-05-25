"""Service layer for collector registration operations."""

from __future__ import annotations

from typing import Dict, List

from domain.collector import Collector
from utils.validation import validate_required_fields


class CollectorService:
    """Handles collector registration and listing."""

    def __init__(self) -> None:
        self._collectors: Dict[str, Collector] = {}

    def register_collector(self, collector_id: str, name: str, service_area: str) -> Collector:
        """Register and return a collector."""
        validate_required_fields(
            {
                "collector_id": collector_id,
                "name": name,
                "service_area": service_area,
            }
        )
        collector = Collector(collector_id=collector_id, name=name, service_area=service_area)
        self._collectors[collector_id] = collector
        return collector

    def list_collectors(self) -> List[Collector]:
        """Return all registered collectors."""
        return list(self._collectors.values())
