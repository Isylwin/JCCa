/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import drawing.domain.Drawing;
import java.util.Properties;

/**
 *
 * @author Oscar
 */
public interface PersistencyMediator {
    Drawing load(String nameDrawing);
    boolean save(Drawing drawing);
    boolean init(Properties props);
}
