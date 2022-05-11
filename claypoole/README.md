# Claypoole

See the `.clj-kondo` directory for configuration for the [Claypoole](https://github.com/TheClimateCorporation/claypoole) library.

The example effectively remaps the following to their `clojure.core` counterparts:



| Original                                   | Remapped  |
|--------------------------------------------|-----------|
| `com.climate.claypoole/future`             | `future`  |
| `com.climate.claypoole/completable-future` | `future`  |
| `com.climate.claypoole/pdoseq`             | `doseq`   |
| `com.climate.claypoole/pmap`               | `map`     |
| `com.climate.claypoole/upmap`              | `map`     |
| `com.climate.claypoole/pvalues`            | `pvalues` |
| `com.climate.claypoole/upvalues`           | `pvalues` |
| `com.climate.claypoole/pfor`               | `for`     |
| `com.climate.claypoole/upfor`              | `for`     |
| `com.climate.claypoole.lazy/pdoseq`        | `doseq`   |
| `com.climate.claypoole.lazy/pmap`          | `map`     |
| `com.climate.claypoole.lazy/upmap`         | `map`     |
| `com.climate.claypoole.lazy/pvalues`       | `pvalues` |
| `com.climate.claypoole.lazy/upvalues`      | `pvalues` |
| `com.climate.claypoole.lazy/pfor`          | `for`     |
| `com.climate.claypoole.lazy/upfor`         | `for`     |
