# Elite Bosses (Forge 1.20.1)

Contiene tres jefes: **Chocolate** (Odio el Chocolate), **Sabueso** (Houndmaster) y **Stalker** (Krasue),
mas los objetos Vito's Blessing, Manual de Supervivencia y Totem BlackFate, y los efectos Obsesion y Salmuera.
Requiere Minecraft 1.20.1 + Forge 47.x. No necesita otros mods (no usa GeckoLib).

## Compilar con GitHub

1. Crea un repositorio nuevo y sube TODO el contenido de esta carpeta (no el zip ni la carpeta envoltorio).
2. Debe existir `.github/workflows/build.yml`. Si no se subio (carpeta oculta), usa
   "Add file > Create new file", escribe `.github/workflows/build.yml` y pega el contenido de `COPIAR_build.yml.txt`.
3. Pestana **Actions** > ejecucion "Compilar mod" > espera el tick verde.
4. Abajo, en **Artifacts**, descarga `elitebosses-jar` (dentro esta `elitebosses-3.0.0.jar`).
5. Borra el jar viejo `odioelchocolate-*.jar` de tu carpeta `mods` y pon el nuevo.

## Comandos

- `/houndmaster` : el Sabueso aparece a 300 bloques del jugador que ejecuta el comando (op nivel 2).
- `/houndmaster <x> <y> <z>` : lo invoca en esas coordenadas.
- `/summon elitebosses:stalker` : el Stalker (tambien con su huevo). Elige al jugador survival mas cercano.
- `/summon elitebosses:houndmaster` o `/summon elitebosses:chocolate` : aparecen donde indiques / donde estas.
- `/chocosound chocochase1 | chocochase2 | chocospawn | stop [jugadores]`
- Huevos: pestana "Elite Bosses" del creativo (y pestana de huevos). Si no los ves:
  `/give @s elitebosses:houndmaster_spawn_egg` y `/give @s elitebosses:chocolate_spawn_egg`, `/give @s elitebosses:stalker_spawn_egg`,
  `/give @s elitebosses:vitos_blessing`, `/give @s elitebosses:survival_manual`, `/give @s elitebosses:black_fate_totem`

## Donde ajustar

- Sabueso: constantes al inicio de `src/main/java/com/elitebosses/entity/HoundmasterEntity.java`.
- Stalker: constantes al inicio de `.../entity/StalkerEntity.java`.
- Chocolate: constantes al inicio de `.../entity/ChocolateEntity.java`.
- Barra de vida: `client/HoundBossBar.java` (SCALE para el tamano).
