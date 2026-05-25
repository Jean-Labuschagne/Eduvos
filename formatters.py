"""Output formatting helpers for console presentation."""


def format_output(title: str, body: str) -> str:
    """Return a simple formatted block for menu output."""
    line = "=" * 60
    return f"\n{line}\n{title}\n{line}\n{body}\n"
