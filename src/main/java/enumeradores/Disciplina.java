package enumeradores;

public enum Disciplina {

    FITNES("Fitnes"),
    ENTRENAMIENTO("Entrenamiento"),
    CICLISMO("Ciclismo"),
    NATACION("Natacion"),
    DEPORTE("Deportes"),
    SENDERISMO("Senderismo");

    private String disciplina;

    Disciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

}
