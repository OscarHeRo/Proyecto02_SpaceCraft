package com.chillizardinteractive.modelo.state;

public class LanzamientoMoneda implements GameState {
    @Override
    public void lanzarMoneda(GameContext context) {
        System.out.println("Lanzando moneda para determinar el primer jugador...");

        // Suponiendo que el jugador 1 elige cara
        String eleccionJugador1 = "cara"; // "cara" o "cruz"
        String resultado = Moneda.lanzarMoneda();

        if (resultado.equals(eleccionJugador1)) {
            System.out.println("Jugador 1 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer1());
        } else {
            System.out.println("Jugador 2 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer2());
        }

        context.setState(new InicioTurno());
        context.iniciarTurno();
    }

    @Override
    public void iniciarJuego(GameContext context) {
        System.out.println("Error: No se puede iniciar el juego en el estado de lanzamiento de moneda.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        System.out.println("Error: No se puede iniciar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void faseCombate(GameContext context) {
        System.out.println("Error: No se puede pasar a la fase de combate en el estado de lanzamiento de moneda.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        System.out.println("Error: No se puede terminar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        System.out.println("Error: No se puede finalizar el juego en el estado de lanzamiento de moneda.");
    }
}