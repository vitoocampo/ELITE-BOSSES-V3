package com.elitebosses.client;

import com.elitebosses.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

/** Manual de Supervivencia: informacion de cada enemigo de Elite Bosses y recetas con dibujo. */
public class ManualScreen extends Screen {
    private static final int PAGE_W = 300;
    private static final int PAGE_H = 200;
    private static final int TEXT_W = PAGE_W - 30;

    private record Section(String title, String body) { }

    private static final List<Section> SECTIONS = List.of(
        new Section("Manual de Supervivencia",
            "Todo lo que se sabe de los enemigos de Elite Bosses.\n\n"
          + "Contenido:\n- Chocolate\n- Sabueso (Houndmaster)\n- Stalker (Krasue)\n- Salmuera y Obsesion\n"
          + "- Objetos: Vito's Blessing, Totem BlackFate\n\nPasa las paginas con las flechas."),
        new Section("Chocolate - vida y fase 1",
            "Vida: 100 corazones (200). Persigue al jugador survival mas cercano (a menos de 32 bloques).\n\n"
          + "FASE 1: algo mas lento que tu corriendo. Cada golpe lo hace un 20% mas rapido y le suma 1 de dano "
          + "(empieza en 2 corazones). Su espera entre golpes se reduce a la mitad con cada golpe (minimo 0.2 s).\n\n"
          + "Pasa a la fase 2 cuando tu vida baja a 5 corazones o menos, o cuando a el le quedan 25 corazones o menos."),
        new Section("Chocolate - fase 2 y furia",
            "FASE 2: te alcanza y te deja en 1 corazon. Se aleja 15 bloques y te da Velocidad II durante 4:30. "
          + "Espera 5 s, mantiene 7-9 bloques de distancia 10 s, te sigue lento 1 min, mas rapido, y los ultimos "
          + "30 s te mata a golpes de 3 corazones (1 s entre golpes).\n\nFURIA: con 5 corazones de vida o menos "
          + "se mueve al doble de velocidad.\n\nSi se acaba tu Velocidad II, desaparece.\n\n"
          + "Drop: 1 Totem de la Inmortalidad."),
        new Section("Chocolate - como pararlo",
            "GALLETA: si tu (o quien te mira) terminas de comer una galleta a 32 bloques o menos, el Chocolate "
          + "desaparece con gritos de ghast, sin soltar nada. Tambien desaparece el que te este persiguiendo "
          + "a ti sin importar la distancia.\n\nLECHE: lo hace desaparecer igual, te quita los efectos... y "
          + "paga el tramposo: 10 s de ceguera, ruidos y textos, y despues mueres.\n\nSu musica suena a 32 bloques."),
        new Section("Sabueso - datos",
            "Vida: 700 corazones (1400). No regenera de ninguna forma. Siempre es muy rapido (~13.8 bloques/s) "
          + "haya combate o no, y va tras el jugador survival mas cercano sin importar la distancia.\n\n"
          + "Rompe bloques naturales en su camino (no cerca de camas ni si mobGriefing esta apagado). "
          + "Cuando mata a un jugador: si no hay otros jugadores a 90 bloques, desaparece sin drop; si los hay, "
          + "duplica su velocidad hasta su siguiente golpe.\n\nMusica: HoundLurk a 120 bloques, HoundChase a 70.\n"
          + "Con 3-4 jugadores a 60 bloques hace un 10% menos de dano; con 5 o mas, un 15% mas."),
        new Section("Sabueso - ataques",
            "ABALANZAR: salta y te muerde. Con armadura de hierro completa te quita 3 corazones.\n\n"
          + "LEVANTAR: gruñe y te lanza 10 bloques al aire.\n\nAGARRE: te sujeta; pulsa ESPACIO 5-25 veces para "
          + "zafarte. Cada segundo 3.5 de dano y desgasta tu armadura (el hierro se rompe a los 5 s).\n\n"
          + "MANADA: aulla y llama 2-5 lobos; mientras tanto recibe un 10% mas de dano 5 s. Con 3 lobos o menos "
          + "estos se potencian y todos los jugadores a 64 bloques reciben Hambre IV.\n\nRETIRADA: con poca vida "
          + "puede huir y llamar 2 lobos extra."),
        new Section("Sabueso - Canalizacion",
            "Cada 30 s en combate tiene un 15% de usarla. Baja la cabeza 5 s (Resistencia 2) y aparece el mensaje "
          + "rojo \"El Sabueso escarba\". Luego invoca un LOBO HOSTIL: 50 corazones, Velocidad 10; cada golpe da "
          + "Lentitud 5 por 5 s.\n\nCada 3 s tiene un 50% de agarrarte (no lo hace si tienes menos de 6 corazones): "
          + "pulsa ESPACIO 30-50 veces. Si solo hay un jugador a 64 bloques del Sabueso te arrastra hacia el; "
          + "si hay mas de uno, te aleja y te mantiene a 30 bloques."),
        new Section("Sabueso - REMATE",
            "Se lanza cuando un jugador tiene menos de 6 corazones. Su unico enfriamiento: haber usado 2 ataques "
          + "especiales desde el ultimo. Entre menos vida tenga, sus ataques especiales son mas seguidos.\n\n"
          + "No se puede detener. Aulla, embiste y: 65% acierta / 35% falla.\n\n"
          + "FALLA (\"REMATE FALLIDO\"): se aturde 3 s y pierde 20 de vida.\nACIERTA: un escudo alzado aguanta "
          + "(se rompe y quedas con 1 corazon). Si no, te lanza; chocar con una estructura es muerte instantanea. "
          + "Si mueres asi (\"REMATE ASESTADO\"), todos a 64 bloques sufren Ceguera 20 s, Hambre 10 7 s, "
          + "Lentitud 1 min y Oscuridad 7 s.\n\nDrop: 15 huesos, 5 diamantes, 2 manzanas de oro, 1 manzana "
          + "encantada, 3 totems."),
        new Section("Stalker - etapa 1",
            "La Krasue, espiritu de la mitologia tailandesa. Etapa 1: tamano de Allay, vuela, INMORTAL, no hace dano. "
          + "Elige al jugador survival mas cercano, le da OBSESION (5 min) y solo lo persigue a el.\n\n"
          + "0:00-2:00: a 32 bloques, parpadea invisible (4 s) / visible (0.8 s).\n2:00-3:00: a 16 bloques, suena "
          + "StalkerStalk1.\n3:00-4:00: a 8 bloques, suena StalkerStalk2.\n4:00-5:00: invisible; Ceguera 30 s, Nausea "
          + "y pierdes 0.5 corazones cada 4 s (ignora armadura). Los ultimos 30 s tu pantalla se oscurece y parpadea "
          + "con visiones y sonidos de carne."),
        new Section("Stalker - etapa 2",
            "Al acabar la Obsesion: 500 corazones, 3 bloques de alto, Resistencia infinita, vuela a 3-8 bloques de "
          + "altura. Solo te persigue a ti hasta que alguno muera. Velocidad de lobo, +1 nivel de Velocidad cada "
          + "2 min hasta Velocidad 4. Con menos de 30% de vida, sus ataques dan Hambre 10 por 2 s. Suena StalkerChase.\n\n"
          + "LATIGO: a distancia, 5 corazones, +1 Salmuera.\nASCENSION VISCERAL: marca el suelo 4x4; a los 2 s "
          + "surgen espinas: 15 de dano y +2 Salmuera. Con 5+ cargas el area es 8x8 y da +4."),
        new Section("Stalker - ataques mayores",
            "RAPTO VISCERAL: te encierra con ella en una celda de barras de hierro (~4x4) 15 s; +1 Salmuera. Si ya "
          + "tienes 15 de Salmuera el suelo es lava (ella es inmune).\n\nPURGA: se eleva 8 bloques y canaliza 30 s "
          + "(\"Purgando\"). Solo si tienes 20+ de Salmuera y menos de 8 corazones. Borra toda la Salmuera y recibes "
          + "2 de dano por carga. Recibir mucho dano la interrumpe.\n\nEJECUCION: canaliza 10 s sin poder detenerla "
          + "(\"INFECCION INMINENTE\"). Si al final tienes 50 de Salmuera, mueres al instante. Beber leche la evita.\n\n"
          + "DRENAJE: con menos de 40% de vida y 5+ Salmuera, te inmoviliza: pulsa ESPACIO 5-10 veces. Cada segundo "
          + "ella cura 4 corazones y tu pierdes 1 de Salmuera."),
        new Section("Salmuera y Obsesion",
            "SALMUERA: efecto acumulable (cada carga sube el nivel). Dura hasta que mueres, la Krasue muere o "
          + "bebes leche.\n\nOBSESION: 5 minutos. Marca al jugador elegido por el Stalker. Si la quitas con leche, "
          + "el Stalker te la vuelve a poner con el tiempo restante.\n\nFINAL: cuando mata a su objetivo aterriza, "
          + "canaliza 8 s y desaparece. Si la matas durante ese rezo: 2 totems. Si la matas antes de que termine "
          + "su trabajo: 1 manzana de oro encantada y 3 totems. Si no, nada."),
        new Section("Vito's Blessing",
            "Estrella de la proteccion. Al usarla, todo mob de Elite Bosses a 128 bloques desaparece sin soltar nada. "
          + "Durante 20 minutos, cualquier mob de Elite Bosses que se acerque a 15 bloques de ti desaparece.\n\n"
          + "Receta (dibujada en la siguiente pagina)."),
        new Section("Totem BlackFate",
            "Lo sueltan los esqueletos Wither con un 20% de probabilidad.\n\nFunciona como un totem normal, pero "
          + "vacia tu barra de hambre y te cura y otorga un 30% menos que el original (regeneracion, resistencia "
          + "al fuego y absorcion reducidas).")
    );

    private final List<Object[]> pages = new ArrayList<>(); // {titulo, List<FormattedCharSequence>|String "recipe"}
    private int page = 0;

    public ManualScreen() {
        super(Component.literal("Manual de Supervivencia"));
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new ManualScreen());
    }

    @Override
    protected void init() {
        pages.clear();
        int maxLines = (PAGE_H - 50) / 10;
        for (Section s : SECTIONS) {
            List<FormattedCharSequence> lines = new ArrayList<>();
            for (String para : s.body().split("\n", -1)) {
                if (para.isEmpty()) {
                    lines.add(FormattedCharSequence.EMPTY);
                } else {
                    lines.addAll(this.font.split(Component.literal(para), TEXT_W));
                }
            }
            int n = Math.max(1, (lines.size() + maxLines - 1) / maxLines);
            for (int i = 0; i < n; i++) {
                int from = i * maxLines, to = Math.min(lines.size(), from + maxLines);
                String title = n > 1 ? s.title() + " (" + (i + 1) + "/" + n + ")" : s.title();
                pages.add(new Object[]{title, lines.subList(from, to)});
            }
            if (s.title().startsWith("Vito")) {
                pages.add(new Object[]{"Receta: Vito's Blessing", "recipe"});
            }
        }
        int left = (this.width - PAGE_W) / 2, top = (this.height - PAGE_H) / 2;
        this.addRenderableWidget(Button.builder(Component.literal("<"), b -> page = Math.max(0, page - 1))
                .bounds(left + 10, top + PAGE_H - 22, 24, 16).build());
        this.addRenderableWidget(Button.builder(Component.literal(">"), b -> page = Math.min(pages.size() - 1, page + 1))
                .bounds(left + PAGE_W - 34, top + PAGE_H - 22, 24, 16).build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics gg, int mouseX, int mouseY, float partial) {
        this.renderBackground(gg);
        int left = (this.width - PAGE_W) / 2, top = (this.height - PAGE_H) / 2;
        gg.fill(left - 3, top - 3, left + PAGE_W + 3, top + PAGE_H + 3, 0xFF4A2E14);
        gg.fill(left, top, left + PAGE_W, top + PAGE_H, 0xFFEADFBE);
        if (!pages.isEmpty()) {
            Object[] pg = pages.get(page);
            gg.drawString(this.font, (String) pg[0], left + 15, top + 12, 0x7A1018, false);
            gg.fill(left + 15, top + 24, left + PAGE_W - 15, top + 25, 0xFF7A5A30);
            if (pg[1] instanceof String) {
                drawRecipe(gg, left, top);
            } else {
                @SuppressWarnings("unchecked")
                List<FormattedCharSequence> lines = (List<FormattedCharSequence>) pg[1];
                int y = top + 32;
                for (FormattedCharSequence line : lines) {
                    gg.drawString(this.font, line, left + 15, y, 0x2A1A0A, false);
                    y += 10;
                }
            }
            String num = (page + 1) + " / " + pages.size();
            gg.drawString(this.font, num, left + PAGE_W / 2 - this.font.width(num) / 2, top + PAGE_H - 18, 0x5A4020, false);
        }
        super.render(gg, mouseX, mouseY, partial);
    }

    private void drawRecipe(GuiGraphics gg, int left, int top) {
        ItemLike[][] grid = {
            {Items.TOTEM_OF_UNDYING, Items.GOLD_BLOCK, Items.TOTEM_OF_UNDYING},
            {Items.GOLDEN_APPLE, Items.WITHER_SKELETON_SKULL, Items.GOLDEN_APPLE},
            {Items.CRYING_OBSIDIAN, Items.SOUL_SAND, Items.CRYING_OBSIDIAN}};
        int gx = left + 40, gy = top + 40;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int x = gx + c * 22, y = gy + r * 22;
                gg.fill(x - 1, y - 1, x + 19, y + 19, 0xFF8B8B8B);
                gg.fill(x, y, x + 18, y + 18, 0xFFC6C6C6);
                gg.renderItem(new ItemStack(grid[r][c]), x + 1, y + 1);
            }
        }
        gg.drawString(this.font, "=>", gx + 74, gy + 26, 0x2A1A0A, false);
        int x = gx + 100, y = gy + 22;
        gg.fill(x - 1, y - 1, x + 19, y + 19, 0xFF8B8B8B);
        gg.fill(x, y, x + 18, y + 18, 0xFFC6C6C6);
        gg.renderItem(new ItemStack(ModItems.VITOS_BLESSING.get()), x + 1, y + 1);
        gg.drawString(this.font, "Mesa de crafteo (3x3)", left + 15, top + 112, 0x5A4020, false);
        gg.drawString(this.font, "Totem / Bloque de oro / Totem", left + 15, top + 126, 0x2A1A0A, false);
        gg.drawString(this.font, "Manzana de oro / Calavera de Wither / Manzana de oro", left + 15, top + 138, 0x2A1A0A, false);
        gg.drawString(this.font, "Obsidiana llorona / Arena de almas / Obsidiana llorona", left + 15, top + 150, 0x2A1A0A, false);
    }
}
