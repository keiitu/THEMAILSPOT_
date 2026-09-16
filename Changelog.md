# Changelog

All notable changes to The Mail Spot.

## [Part 1] - 2026-09-15

### Added
- Username validation (underscore, ≤ 5 characters)
- Password complexity validation (≥ 8 chars, capital, number, special)
- South African cell phone validation (+27 format)
- Registration flow with input validation loops
- Login verification
- Unit tests for all validation methods

### Fixed
- Special character detection logic (AND vs OR)
- JUnit/Hamcrest classpath configuration
- Branch renaming from master to main

### Documentation
- Added README with project context
- Added .gitignore for NetBeans/Ant projects
