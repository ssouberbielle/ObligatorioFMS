import LinkedList.LinkedList;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import hash.ClosedHashImpl;
import hash.HashEntry;
import nodo.Node;
import nodo.Wrapper;

public class LanguageTimes implements Comparable <LanguageTimes> {  // FIXME despues hacemos un array de objetos de tipo LanguageTimes

    private String lang;
    private int reserveNumber;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getReserveNumber() {
        return reserveNumber;
    }

    public void setReserveNumber(int reserveNumber) {
        this.reserveNumber = reserveNumber;
    }

    public LanguageTimes(String lang) {
        this.lang = lang;
        this.reserveNumber = 0;
    }

    public static void Consulta4(Book[] books, ClosedHashImpl<Long, User> users) {

        ClosedHashImpl<String, String> languages = new ClosedHashImpl<>(40);
        for (Book b : books) {
            if (!b.getLanguage().equals( "nan")) {
                if (!languages.contains(b.getLanguage())) { // si no existe todavia el lenguaje
                    languages.put(b.getLanguage(), "0"); // Este valor es inutil
                }
            }
        }
        int size = languages.filledBuckets();

        // Todo hasta aca es para saber cuantos idiomas son y ver la dimension del array
        LanguageTimes[] tempArray = new LanguageTimes[size];
        int i = 0;

        for (HashEntry<String, String> nodo: languages.getHashTable()) {
            if (nodo != null) {
                    LanguageTimes lt = new LanguageTimes(nodo.getKey());
                    tempArray[i] = lt;
                    i++;
                }
            }


        for (LanguageTimes lts : tempArray) {
            for (Book bk : books) {
                if (bk.getLanguage().equals(lts.lang)) {
                    lts.setReserveNumber(lts.getReserveNumber() + bk.getReserveNum());

                }
            }
        }
        Sorting.mergeSort(tempArray, tempArray.length);

        for (int language = tempArray.length - 1; language > tempArray.length - 6; language--) {
            System.out.println("Código del idioma " + tempArray[language].getLang());
            System.out.println("Cantidad " + tempArray[language].getReserveNumber());
            System.out.println("\n");


        }
    }


    @Override
    public int compareTo(LanguageTimes o) {
        return this.reserveNumber - o.reserveNumber;
    }
}
