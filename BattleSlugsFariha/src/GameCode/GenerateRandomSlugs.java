/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

/**
*
* @author Mainul35
*
*/

public class GenerateRandomSlugs {

    private static int i = 1;	//count the number of segments in each block of grids.

    /**
     *
     * getValues method returns 5 segments of each slug as a hashmap.
     *
     * @param x is the randomly generated x coordinate for a block
     * @param y is the randomly generated y coordinate for a block
     * @param min_x is the minimum value of x axis for a block
     * @param min_y is the minimum value of y axis for a block
     * @param max_x is the maximum value of x axis for a block
     * @param max_y is the maximum value of y axis for a block
     *
     * @return Hasmap<String key, Integer value>
     *
     *
     */
    public HashMap<String, Integer> getValues(int x, int y, int min_x, int min_y, int max_x, int max_y, HashMap<String, Integer> hm) {

        if (i < 6) {
            if (x >= min_x && x <= max_x) {
                if (y >= min_y && y <= max_y) {
                    if (!hm.containsKey(x + " " + y)) {
                        hm.put(x + " " + y, i);
                        i++;
                        getValues(x, y, min_x, min_y, max_x, max_y, hm);
                        return hm;
                    } else if (x - 1 >= min_x && !hm.containsKey((x - 1) + " " + y)) {
                        x = x - 1;
                        hm.put(x + " " + y, i);
                        i++;
                        getValues(x, y, min_x, min_y, max_x, max_y, hm);
                        return hm;
                    } else if (x + 1 <= max_x && !hm.containsKey((x + 1) + " " + y)) {
                        x = x + 1;
                        hm.put(x + " " + y, i);
                        i++;
                        getValues(x, y, min_x, min_y, max_x, max_y, hm);
                        return hm;
                    } else if (y - 1 >= min_y && !hm.containsKey(x + " " + (y - 1))) {
                        y = y - 1;
                        hm.put(x + " " + y, i);
                        i++;
                        getValues(x, y, min_x, min_y, max_x, max_y, hm);
                        return hm;
                    } else if (y + 1 <= max_y && !hm.containsKey(x + " " + (y + 1))) {
                        y = y + 1;
                        hm.put(x + " " + y, i);
                        i++;
                        getValues(x, y, min_x, min_y, max_x, max_y, hm);
                        return hm;
                    }
                }
            }
        }
        i = 0;
        return hm;
    }

    /**
     *
     * the randInt method generates a random value between minimum and maximum
     * value of an axis.
     *
     * @return randNum
     * @param min minimum value of an axis
     * @param max maximum value of an axis
     *
     *
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     *
     * generateSlugLocations function combines all blocks into one block This is
     * how all 5 slugs can be placed on the board.
     *
     * @param hm an empty HashMap<String, Integer>
     * @param click click index
     * @return HashMap<String, Integer> of 25 segments of slugs
     *
     *
     */
    public HashMap<String, Integer> generateSlugLocations(HashMap<String, Integer> hm, int click) {
        GenerateRandomSlugs slugPositions = new GenerateRandomSlugs();
        int x, y;
        if (click == 1) {
            x = slugPositions.randInt(0, 4);
            y = slugPositions.randInt(0, 4);
            hm.putAll(slugPositions.getValues(x, y, 0, 0, 4, 4, hm));
        } else if (click == 2) {
            x = slugPositions.randInt(5, 9);
            y = slugPositions.randInt(0, 4);
            hm.putAll(slugPositions.getValues(x, y, 5, 0, 9, 4, hm));
        } else if (click == 3) {
            x = slugPositions.randInt(0, 4);
            y = slugPositions.randInt(5, 9);
            hm.putAll(slugPositions.getValues(x, y, 0, 5, 4, 9, hm));
        } else if (click == 4) {
            x = slugPositions.randInt(5, 9);
            y = slugPositions.randInt(5, 9);
            hm.putAll(slugPositions.getValues(x, y, 5, 5, 9, 9, hm));
        } else if(click == 5){
            x = slugPositions.randInt(10, 11);
            y = slugPositions.randInt(2, 10);
            hm.putAll(slugPositions.getValues(x, y, 10, 0, 11, 11, hm));
        }
        return hm;
    }

    /**
     *
     * setSlugsToButtons function places the slugs in a 12x12 bute type variable
     * for further use.
     *
     * @param vals[][] byte type 12x12 size array
     * @param hm HashMap<String, Integer> that contains all 25 slug segments.
     *
     * @return vals 12x12 size array containing all slugs in it.
     *
     *
     */

    public byte[][] setSlugsToButtons(byte[][] vals, HashMap<String, Integer> hm) {
        for (Map.Entry<String, Integer> m : hm.entrySet()) {
            StringTokenizer stk = new StringTokenizer(m.getKey());
            // System.out.println(m.getKey()+"\t"+m.getValue());
            vals[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = Byte
                    .parseByte(m.getValue().toString());
        }
        return vals;
    }

    /**
     * getElementByIndex method returns an object value of HashMap according to index call.
     * 
     * @param HashMap<String, Integer> receives the HashMap containing value.
     * @param index receives the index, which value to extract
     * 
     * @return Object value
     * 
     */
    public static Object getElementByIndex(HashMap<String, Integer> map, int index) {
        return map.get(map.keySet().toArray()[index]);
    }
}
