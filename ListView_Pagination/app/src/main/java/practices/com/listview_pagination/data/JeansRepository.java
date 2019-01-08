package practices.com.listview_pagination.data;

import java.util.ArrayList;
import java.util.List;

import practices.com.listview_pagination.model.Jean;

public class JeansRepository {

    //region Member variables
    private List<Jean> jeans;
    //endregion

    //region Constructors
    public JeansRepository() {
        jeans =  new ArrayList<>();
    }
    //endregion

    //region Helper methods
    private void initializeJeans(){
        jeans =  new ArrayList<>();
        Jean jean1 = new Jean("Pantalón Verde", "XS", 203.99);
        Jean jean2 = new Jean("Pantalón Azul", "S", 199.99);
        Jean jean3 = new Jean("Pantalón Rojo", "XS", 159.99);
        Jean jean4 = new Jean("Pantalón Verde", "M", 149.99);
        Jean jean5 = new Jean("Pantalón Amarillo", "M", 99.99);
        Jean jean6 = new Jean("Pantalón Morado", "S", 129.99);
        Jean jean7 = new Jean("Pantalón Cafe", "S", 139.99);
        Jean jean8 = new Jean("Pantalón Negro", "XL", 109.99);
        Jean jean9 = new Jean("Pantalón Azul", "XS", 169.99);

        jeans.add(jean1);
        jeans.add(jean2);
        jeans.add(jean3);
        jeans.add(jean4);
        jeans.add(jean5);
        jeans.add(jean6);
        jeans.add(jean7);
        jeans.add(jean8);
        jeans.add(jean9);
    }

    public List<Jean> getJeans(int pageNumber, int pageSize){
        initializeJeans();
        int start = Math.min(jeans.size(), Math.abs(pageNumber * pageSize));
        int end = Math.min(jeans.size(), Math.abs(start + pageSize));
        return jeans.subList(start, end);
    }
    //endregion


}
