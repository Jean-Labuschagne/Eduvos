"""Exception types used across the GreenTrack application."""


class ValidationException(Exception):
    """Raised when required input validation fails."""


class InvalidWeightException(ValidationException):
    """Raised when a weight value is zero or negative."""


class InvalidWasteTypeException(ValidationException):
    """Raised when a waste type is unsupported."""
