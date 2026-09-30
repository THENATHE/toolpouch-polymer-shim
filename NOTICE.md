# Source attribution and relationship to upstream

This is an unofficial Polymer compatibility shim maintained by THENATHE. It is neither the original Tool Pouch mod nor Polymer, and no endorsement by either upstream project is implied.

## Original projects and artwork

- **Tool Pouch**, by pajic: https://github.com/pajicadvance/toolpouch — MIT, copyright (c) 2026 pajic. Its complete original notice is retained in [licenses/Tool-Pouch-LICENSE.txt](licenses/Tool-Pouch-LICENSE.txt) and `src/main/resources/licenses/toolpouch-LICENSE.txt`.
- **Polymer**, by Patbox and contributors: https://github.com/Patbox/polymer — LGPLv3. Polymer is an external dependency; it is not bundled or relicensed as part of this MIT shim.
- **MapStitch**, by pajic: https://github.com/pajicadvance/mapstitch — the original atlas mod, handled through its separate Polymer shim when installed.

Pouch artwork belongs to the original Tool Pouch project. The shim reads it from the installed original mod during Polymer resource-pack generation; it does not embed or claim authorship of that artwork. The generated pack includes the original MIT license at `licenses/toolpouch/LICENSE` and credits Tool Pouch. The source mod JAR remains unchanged.

## Shim implementation

The shim's MIT license and contribution copyright are recorded in [LICENSE](LICENSE): copyright (c) 2026 THENATHE. Registry negotiation, per-client wire IDs, and optional native-client handling adapt the related MIT-licensed [MapStitch Polymer Shim](https://github.com/THENATHE/mapstitch-polymer-shim), also maintained by THENATHE.

Tool Pouch item behavior and inventory storage remain implemented by the original mod. This repository supplies compatibility overlays, packet/registry handling, use guards, and notices. The separate [Atlas & Elytra Modification](https://github.com/THENATHE/toolpouch-atlas-elytra-modification) owns the optional gameplay additions.

## Distribution

Original mod JARs, Polymer, and other dependency JARs are neither committed nor bundled. Users obtain them separately from their authors. Runtime integration does not rewrite the original files. Fabric and other dependencies retain their respective licenses. Report shim-specific issues here; use upstream issue trackers for problems reproduced without the shim.
