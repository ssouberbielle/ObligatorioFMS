import LinkedList.LinkedList;
import hash.ClosedHashImpl;
import hash.HashEntry;
import nodo.Node;
import nodo.Wrapper;

public class LanguageTimes implements Comparable <LanguageTimes>{  // FIXME despues hacemos un array de objetos de tipo LanguageTimes

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

    public LanguageTimes(String lang ){
        int reserveNumber = 0;
    }

    public static void Consulta4 (Book[] books, ClosedHashImpl<Long, User> users ) {

        //////////////////////////////////////////////////////////////////////////////////////////////

        ClosedHashImpl<String, String> languages = new ClosedHashImpl<>(40);
        for (Book b : books) {
            if (!languages.contains(b.getLanguage())) { // si no existe todavia el lenguaje
                languages.put(b.getLanguage(), "0"); // Este valor es inutil
            }
        }
        int size = languages.filledBuckets();

        // Todo hasta aca es para saber cuantos idiomas son y ver la dimension del array
        LanguageTimes[] tempArray  = new LanguageTimes[size];
        int i = 0;
        for (String idiomas: languages) {
            LanguageTimes lt = new LanguageTimes(idiomas);
            tempArray[i] = lt;
            i++;
        }
        for (LanguageTimes lts : tempArray) {
            for (Book bk : books) {
                if (bk.getLanguage().equals(lts.lang)) {
                    lts.setReserveNumber(lts.getReserveNumber() + bk.getReserveNum());

                }
            }
        }
        Sorting.mergeSort(tempArray, tempArray.length);



        //


        }



    @Override
    public int compareTo(LanguageTimes o) {
        return this.reserveNumber - o.reserveNumber;
    }


    //LinkedList<Wrapper<String>> language = new LinkedList<Wrapper<String>>();
        //Node<LinkedList<Wrapper<String>>> node = new Node<LinkedList<Wrapper<String>>>()
    }

