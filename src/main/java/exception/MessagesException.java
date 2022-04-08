package exception;

public enum MessagesException {

    NoDimensionsMapFound("Dimensions de carte non fournie ou incorrecte dans le fichier en parametre"),
    InvalidInputFileException("Le fichier en entree ne possede pas le bon format"),
    IncorrectMovementException("Un mouvement du joueur non autorise a ete ignore"),
    IncorrectDirectionException("La direction est incorrecte"),
    SquareIsMountainOrTakenException("La case est deja prise par une montagne ou un aventurier, celui ci ne sera pas place sur la carte");

    private String msg;

    MessagesException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
