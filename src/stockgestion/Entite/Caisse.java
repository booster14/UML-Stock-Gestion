package stockgestion.Entite;

import java.util.List;

public class Caisse {

    private boolean etat;
    private List<Client> listClients;

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public List<Client> getListClients() {
        return listClients;
    }

    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }
}