/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forwardbackward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.*;

/**
 *
 * @author cheryl
 */
public class ForwardBackward {
    public static String input_sentence;
    private static String[] input_words;
    private static HashMap<String, Double[]> DynamicTableForward;
    private static HashMap<String, Double[]> DynamicTableBackward;
    private static HashMap<String, Double[]> GammaTable; // string is state
    private static HashMap<String, HashMap<String, Double[]>> EpsilonTable; //first string is i state, second string is j state
            
    private static HashMap<String, HashMap<String, Double>> EmissionTable;
    private static HashMap<String, HashMap<String, Double>> TransmissionTable;
    private static HashMap<String, Double> InitializationTable;
    
    public ForwardBackward (String input, 
            HashMap<String, Double> initializationTable, 
            HashMap<String, HashMap<String, Double>> transmissionTable,
            HashMap<String, HashMap<String, Double>> emissionTable) {
        input_sentence = input;
        Initialization(initializationTable, transmissionTable, emissionTable);
    }
    
    public HashMap<String, HashMap<String, Double>>  GetEmissionTable() {
        return EmissionTable;
    } 
    
    public HashMap<String, HashMap<String, Double>>  GetTransmissionTable() {
        return TransmissionTable;
    } 
    
    public HashMap<String, Double>  GetInitializationTable() {
        return InitializationTable;
    } 
    
    public HashMap<String, Double[]> GetGammaTable() {
        return GammaTable;
    }
    
    public HashMap<String, HashMap<String, Double[]>> GetEpsilonTable() {
        return EpsilonTable;
    }
    
    public static void Initialization(HashMap<String, Double> initializationTable, 
            HashMap<String, HashMap<String, Double>> transmissionTable,
            HashMap<String, HashMap<String, Double>> emissionTable){
        InitializationTable = initializationTable;
        TransmissionTable = transmissionTable;
        EmissionTable = emissionTable;
    }
    
    private static void FillColumnForward(int colNum) {
        String word = input_words[colNum];
        
        if ( colNum == 0 ) { // fill first Column
            for (String key : DynamicTableForward.keySet()) {
                DynamicTableForward.get(key)[colNum] = InitializationTable.get(key) * EmissionTable.get(key).get(word);
            }
        }
        else {
            for (String currentStepKey: DynamicTableForward.keySet()) {
                List<Double> transitFromPreviousList = new ArrayList<>();
   
                for (String previousStepKey: DynamicTableForward.keySet()) {
                    double transitFromPrevious;
                    transitFromPrevious = DynamicTableForward.get(previousStepKey)[colNum-1]*
                            TransmissionTable.get(previousStepKey).get(currentStepKey);
                    transitFromPreviousList.add(transitFromPrevious);
                }         
                double sumValue = transitFromPreviousList.stream().mapToDouble(f -> f.doubleValue()).sum();
             
                double current = (double)-1;

                current = sumValue*EmissionTable.get(currentStepKey).get(word);
 
                DynamicTableForward.get(currentStepKey)[colNum] = current;
            }   
        }
    }
    
    private static void FillColumnBackward(int colNum) {
        if ( colNum == input_words.length - 1) { // fill last Column
            for (String key : DynamicTableBackward.keySet()) {
                DynamicTableBackward.get(key)[colNum] = (double)1;
            }
        }
        else {
            for (String previousStepKey: DynamicTableBackward.keySet()) {
                List<Double> transitToPreviousList = new ArrayList<>();
                
                for (String currentStepKey: DynamicTableBackward.keySet()) {
                    
                    double transitFromPrevious;
                    String current_word = input_words[colNum+1];
                        
                    transitFromPrevious = TransmissionTable.get(previousStepKey).get(currentStepKey) *
                            EmissionTable.get(currentStepKey).get(current_word) *
                            DynamicTableBackward.get(currentStepKey)[colNum+1];
                    transitToPreviousList.add(transitFromPrevious);
                }   
                double sumValue = transitToPreviousList.stream().mapToDouble(f -> f.doubleValue()).sum();

                DynamicTableBackward.get(previousStepKey)[colNum] = sumValue;
            } 
        }
    }
    

    
    private static void FillGammaTable() {
        double P_O_theta_forward = 0;
        int last_col_idx = input_words.length - 1;
        for (String key: DynamicTableForward.keySet()) {
            P_O_theta_forward += DynamicTableForward.get(key)[last_col_idx];
        }
                
//        double P_O_theta_backward = 0;
//        String first_col_word = input_words[0];
//        for (String key: DynamicTableBackward.keySet()) {
//            P_O_theta_backward += DynamicTableBackward.get(key)[0] * 
//                    InitializationTable.get(key) *
//                    EmissionTable.get(key).get(first_col_word);
//        }
        
//        System.out.println("P_O_theta_forward: " + P_O_theta_forward );
//        System.out.println("P_O_theta_backward: " + P_O_theta_backward );
        
        for(String state: GammaTable.keySet()) {
            for (int i = 0; i < GammaTable.get(state).length; i++) {
                double alpha_i = DynamicTableForward.get(state)[i];
                double beta_i = DynamicTableBackward.get(state)[i];
                double gamma_i = alpha_i * beta_i/P_O_theta_forward;
                GammaTable.get(state)[i] = gamma_i;           
            }
        }
    }
    
    private static void FillEpsilonTable() {
        double P_O_theta_forward = 0;
        int last_col_idx = input_words.length - 1;
        for (String key: DynamicTableForward.keySet()) {
            P_O_theta_forward += DynamicTableForward.get(key)[last_col_idx];
        }       
        
        System.out.println("P_O_theta_forward: " + P_O_theta_forward );
//        System.out.println("P_O_theta_backward: " + P_O_theta_backward );
        
        for(String state_i: EpsilonTable.keySet()) {
            HashMap<String, Double[]> state_i_row = new HashMap<>();
            for(String state_j: EpsilonTable.get(state_i).keySet() ) {
                Double[] value_list = new Double[input_words.length];
                for (int i = 0; i < EpsilonTable.get(state_i).get(state_j).length; i++) {
                    
                    if (i == EpsilonTable.get(state_i).get(state_j).length-1) {
                        value_list[i] = (double)0;
                    }
                    else {
                        double alpha_i = DynamicTableForward.get(state_i)[i];
                        double beta_j = DynamicTableBackward.get(state_j)[i+1];
                        double a_ij = TransmissionTable.get(state_i).get(state_j);
                        String word = input_words[i+1];
                        double b_j = EmissionTable.get(state_j).get(word);
                        double epsilon_i = alpha_i * a_ij * b_j * beta_j/P_O_theta_forward;

                        value_list[i] = epsilon_i;      
                    }
                }
                state_i_row.put(state_j, value_list);
            }
            EpsilonTable.put(state_i, state_i_row);
        }
    }
    
        // print functions
    private static void Print2DHash(HashMap<String, HashMap<String, Double>> table) {
        for (String i: table.keySet()) {
            for (String j: table.get(i).keySet()) {
                System.out.print(table.get(i).get(j) + "    ");
            }
            System.out.print('\n');
        }
    }
    
    private static void Print1DHash(HashMap<String, Double> table) {
        for (String i: table.keySet()) {
            System.out.print(table.get(i) + "    ");
        }
    }
    
    private static void Print2DTable(HashMap<String, Double[]> table) {
        for (String key: table.keySet()) {
            for (Double value: table.get(key)) {
                System.out.print(value + "   ");
            }
            System.out.print('\n');
        }
    }
    
    private static void Print3DTable(HashMap<String, HashMap<String, Double[]>> table) {
        for (String state_i: table.keySet()) {
            for (String state_j: table.get(state_i).keySet()) {
                Double[] values = table.get(state_i).get(state_j);
                
                NumberFormat nf = new DecimalFormat("0.#######");
                for (double value : values) {
                    System.out.println("state_i=" + state_i + " state_j=" + state_j + " " +  nf.format(value));
                }
            }
            System.out.print('\n');
        }
    }
    
    // one round
    public static void OneRound() {
        input_words = input_sentence.split("\\s+");
        int sentenceLen = input_words.length;
        
        DynamicTableForward = new HashMap<>();
        DynamicTableForward.put("C", new Double[sentenceLen]);
        DynamicTableForward.put("N", new Double[sentenceLen]);
        DynamicTableForward.put("V", new Double[sentenceLen]);
        DynamicTableForward.put("J", new Double[sentenceLen]);
        
        DynamicTableBackward = new HashMap<>();
        DynamicTableBackward.put("C", new Double[sentenceLen]);
        DynamicTableBackward.put("N", new Double[sentenceLen]);
        DynamicTableBackward.put("V", new Double[sentenceLen]);
        DynamicTableBackward.put("J", new Double[sentenceLen]);
        
        // fill forward table 
        for (int i = 0; i < input_words.length; i++) {
            FillColumnForward(i);
        }

        // fill backward table
        for (int i = input_words.length-1;i>=0; i--) {
            FillColumnBackward(i);
        }

        System.out.println("Print Forward Table");
        Print2DTable(DynamicTableForward);
        System.out.println();
        
        System.out.println("Print Backward Table");
        Print2DTable(DynamicTableBackward);
        System.out.println();
        
        // get gamma table
        GammaTable = new HashMap<>();
        GammaTable.put("C", new Double[sentenceLen]);
        GammaTable.put("N", new Double[sentenceLen]);
        GammaTable.put("V", new Double[sentenceLen]);
        GammaTable.put("J", new Double[sentenceLen]);
        
        FillGammaTable();
        
        System.out.println("Print Gamma Table");
        Print2DTable(GammaTable);
        
        // get epsilon table
        EpsilonTable = new HashMap<>();
        HashMap<String, Double[]> state_j_row = new HashMap<>();
        state_j_row.put("C", new Double[sentenceLen]);
        state_j_row.put("N", new Double[sentenceLen]);
        state_j_row.put("V", new Double[sentenceLen]);
        state_j_row.put("J", new Double[sentenceLen]);
        EpsilonTable.put("C", state_j_row);
        EpsilonTable.put("N", state_j_row);
        EpsilonTable.put("V", state_j_row);
        EpsilonTable.put("J", state_j_row); 
        
        FillEpsilonTable();
        Print3DTable(EpsilonTable);
    }  
}
