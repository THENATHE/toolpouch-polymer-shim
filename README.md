> **Archived on 2026-10-04.** Future combined Minecraft 26.3 development continues in [Vanilla++ Quality of Life Suite](https://github.com/THENATHE/vanilla-plusplus-quality-of-life-suite). Existing standalone releases and source remain available here.
>
> The suite incorporates this component. Existing standalone installations remain a separate option; follow the suite installation instructions when migrating.

# Tool Pouch Polymer Shim

An **unofficial server-side Polymer compatibility shim** for [Tool Pouch](https://github.com/pajicadvance/toolpouch). Players with the mod retain native items, menus, and gameplay. Players without it can join the same server and see safe pouch placeholders with an install notice, without using the pouch interface or its automatic contents lookup.

**Minecraft 26.3 · Fabric · Tool Pouch 1.1.10 · Polymer 0.18.2+26.3**

[Download the Polymer shim](https://github.com/THENATHE/toolpouch-polymer-shim/releases/latest) · [Atlas & Elytra modification](https://github.com/THENATHE/toolpouch-atlas-elytra-modification) · [Validation](docs/VALIDATION.md)

Tool Pouch is created by **pajic**; Polymer is created by **Patbox and contributors**. This independent add-on is maintained by THENATHE. It is not an official release of either project and does not replace or republish their mods. See [NOTICE.md](NOTICE.md) for source attribution and artwork licensing.

## Features

### Native Tool Pouch clients

- Preserve the real regular/netherite pouch items, data components, attached-leggings data, and custom pouch menu.
- Keep supported Tool Pouch networking and normal mod behavior available to clients that advertise the mod's channels.
- Preserve authoritative server items and stored contents when items are displayed, transferred, or edited through native menus.
- Work with the original Tool Pouch 1.1.10 JAR, the earlier combined atlas/Elytra build, and the separate [Atlas & Elytra Modification](https://github.com/THENATHE/toolpouch-atlas-elytra-modification).

### Clients without Tool Pouch

- Allow vanilla clients and Fabric clients without Tool Pouch to connect despite Tool Pouch's custom item, component, menu, and recipe registrations.
- Display both pouch types with the **original Tool Pouch artwork and dye tint** when the Polymer resource pack is accepted. Without it, a named leather item remains readable and safe.
- Add the notice **“You need the Tool Pouch mod on your client to use this.”** to pouch lore and the action bar.
- Show the notice while holding or equipping a pouch, or wearing leggings with an attached pouch, including when both hands are empty. It repeats approximately every ten seconds while relevant.
- Block the custom pouch menu and automatic pouch-content lookup for flight, totems, ammunition, and maps. Right-clicking provides the install notice.
- Let unsupported players remove filled attached leggings even when Tool Pouch's filled-pouch unequip restriction is enabled. Normal vanilla equipment restrictions still apply.
- Keep the server's actual pouch and contents intact, including when the player later reconnects with the mod installed.

### Mixed Tool Pouch and MapStitch clients

An explicit compatibility bridge coordinates registry negotiation with the [MapStitch Polymer Shim](https://github.com/THENATHE/mapstitch-polymer-shim). Each client's Tool Pouch and MapStitch capabilities are handled separately. For example, a Tool Pouch client without MapStitch can use a native pouch containing a display-only atlas; the server retains the actual atlas when the pouch is edited.

## Install

Install `toolpouch-polymer-compat-1.0.0+26.3.jar` **on the server only**, alongside the original Tool Pouch mod, its normal dependencies, and Polymer. Native clients install Tool Pouch normally; vanilla clients install nothing.

| Dependency | Tested version | Role |
| --- | --- | --- |
| Minecraft / Java | 26.3 / 25 | Runtime |
| Fabric Loader / Fabric API | 0.19.5 / 0.161.0+26.3 | Loader, events, registry negotiation, and networking |
| Tool Pouch | 1.1.10 | Required original mod |
| Fzzy Config | 0.7.7+fix2+26.3 | Upstream configuration dependency |
| Fabric Language Kotlin | 1.14.1+kotlin.2.4.20 | Required by the tested dependency stack |
| Polymer bundled | 0.18.2+26.3 | Includes Core, resource-pack, and registry support |
| MapStitch Polymer Shim | 1.0.1+26.3 | Required for vanilla compatibility when MapStitch itself is also installed |

Use exactly one Tool Pouch JAR. Matching original mod builds on the server and native clients are recommended. Do not replace the original mods with this shim.

### Original artwork

After installing the shim, run `/polymer generate-pack` and deliver `polymer/resource_pack.zip` through your normal Polymer pack hosting. Clients must accept the pack to see the original pouch artwork. The shim obtains assets from the installed Tool Pouch JAR at pack-generation time and includes the original MIT notice in the generated pack; the original JAR is not modified.

### Optional atlas and Elytra features

Install the separate [Tool Pouch Atlas & Elytra Modification](https://github.com/THENATHE/toolpouch-atlas-elytra-modification) on the server and participating modded clients to add pouch atlas integration and the flight toggle. This Polymer shim works without that modification; the modification also works without this shim on an ordinary modded server.

If MapStitch is installed on a mixed-client server, its own [Polymer shim](https://github.com/THENATHE/mapstitch-polymer-shim) is needed too. The Tool Pouch shim coordinates with it but does not replace it.

## How it works

Polymer item overlays change only what is sent to each client. Fabric's existing channel negotiation identifies native Tool Pouch clients before registry synchronization, and the result is fixed for that connection. Native clients receive their mod registrations and data; unsupported clients receive compatible item representations and guarded interactions.

A per-connection registry map translates item, data-component, menu, and recipe-serializer IDs in outgoing and incoming packets. Optional hooks into the MapStitch Polymer Shim prevent duplicate registry translation and duplicate capability negotiation when both shims are installed. The server keeps the original registered objects and saved item data throughout.

## Direct compatibility layers

| Project | What this shim directly integrates |
| --- | --- |
| [Tool Pouch](https://github.com/pajicadvance/toolpouch) | Item overlays, component/menu/recipe registrations, supported packet delivery, menu/use guards, passive lookup guards, and equipped-pouch notices. |
| [Polymer](https://github.com/Patbox/polymer) | Core item/component APIs, resource-pack assembly, and registry synchronization support. |
| [Fabric API](https://github.com/FabricMC/fabric) | Client channel negotiation, connection contexts, registry synchronization, events, and networking. |
| [MapStitch Polymer Shim](https://github.com/THENATHE/mapstitch-polymer-shim) | Optional shared registry handling while preserving independent native capabilities for each mod. |

The [Atlas & Elytra Modification](https://github.com/THENATHE/toolpouch-atlas-elytra-modification) is a tested companion, not a required dependency or a dedicated target of this shim's mixins. Accessory detection delegates to Tool Pouch's existing adapters. There are no new dedicated integrations here for Trinkets, Curios, Ohmega, Aileron, or Shared Region Maps.

## Build and validation

Use a Java 25 JDK. Place the two compile-only JARs listed in [libs/README.md](libs/README.md) in `libs/`, then run:

```sh
./gradlew build
```

Windows: `gradlew.bat build`. Output: `build/libs/toolpouch-polymer-compat-1.0.0+26.3.jar`. Neither original mods nor the other shim are bundled. A newer compiler can target Java 25 with `-PcompilerVersion=27` while Gradle runs on Java 25.

Native, unsupported Fabric, and unmodified vanilla connections were tested, including native menu edits, independently negotiated MapStitch items, equipped-armor notices, and original-artwork pack generation. See [the validation record](docs/VALIDATION.md). The registry bridge targets the tested Fabric/Polymer versions and MapStitch Polymer Shim 1.0.1; changes to those internals require retesting. Report shim-specific problems in [this repository's issues](https://github.com/THENATHE/toolpouch-polymer-shim/issues).
