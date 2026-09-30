# Validation — 1.0.0+26.3

Recorded 2026-09-30 for Fabric Minecraft 26.3, Fabric API 0.161.0+26.3, Polymer 0.18.2+26.3, and optional MapStitch Polymer Shim 1.0.1+26.3. Release SHA-256:

`4f69a66f4a63290ed5c5b61572924f1d81064540346e0ce21e5178715f3b1cfd`

| Scenario | Verified behavior |
| --- | --- |
| Original Tool Pouch + Polymer shim, native Tool Pouch client | Real pouch IDs/components, no install notice, native menu opening, compass extraction/reinsertion, saved contents, and pouch flight eligibility. |
| Both add-ons + server MapStitch; client has Tool Pouch but no MapStitch | Native pouch/menu with display-only nested atlas; server retains the actual atlas after menu editing. |
| Both add-ons + MapStitch; unmodified vanilla client | Joins using Mojang's normal client entry point with no client mods/agents; safe placeholders, real right-click notice, no mod menu, preserved server contents, and no pouch flight. |
| Earlier combined Tool Pouch build + Polymer shim; client has MapStitch but no Tool Pouch | Leather pouch fallback alongside native atlas data, blocked pouch menu, preserved contents, and notice while attached leggings are equipped with both hands empty. |
| Generated Polymer resource pack | Valid ZIP with both item definitions, both pouch models, four item textures, and the original license. All eight pouch item-definition/model/texture files match the original JAR byte-for-byte. |

An earlier build also passed an unsupported Fabric-client check. Its separate hash is retained in [validation-summary.json](validation-summary.json); the final release is covered by the scenarios above. Native atlas rendering with both shims was also verified by the [modification's 208-assertion suite](https://github.com/THENATHE/toolpouch-atlas-elytra-modification/blob/main/docs/VALIDATION.md).

All recorded original Tool Pouch JARs and actual launched mod copies remained unchanged. The overarching release check also confirmed all twelve recorded original Tool Pouch/MapStitch-related JAR checksums.

Artwork validation checks generated structure and byte identity; it is not a claim of a human visual review of every accepted-pack dye variant. Third-party accessory integrations, every creative-mode interaction, and every upstream mod feature were not exhaustively tested. The tested native paths preserve the original implementation rather than replacing it.

The local harness is not a portable part of this repository. `./gradlew build` compiles/packages the shim and does not rerun the recorded connected-client matrix. Raw worlds, logs, dependency JARs, and local environment/account paths are not published.
