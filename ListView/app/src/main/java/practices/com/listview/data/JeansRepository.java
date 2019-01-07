package practices.com.listview.data;


import java.util.ArrayList;
import java.util.List;

import practices.com.listview.model.Jean;

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
        Jean jean1 = new Jean("Pantalon Verde", "XS", 203.99);
        Jean jean2 = new Jean("Pantalon Azul", "S", 199.99);
        Jean jean3 = new Jean("Pantalon Rojo", "XS", 159.99);
        Jean jean4 = new Jean("Pantalon Verde", "M", 149.99);
        Jean jean5 = new Jean("Pantalon AzuL", "L", 99.99);

        jeans.add(jean1);
        jeans.add(jean2);
        jeans.add(jean3);
        jeans.add(jean4);
        jeans.add(jean5);
    }

    public List<Jean> getJeans(){
        initializeJeans();
        return jeans;
    }
    //endregion


}
