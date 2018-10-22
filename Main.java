/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forwardbackward;

import static forwardbackward.ForwardBackward.input_sentence;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author cheryl
 */
public class Main {
    private static HashMap<String, HashMap<String, Double>> SumEmissionTable_numerator;
    private static HashMap<String, HashMap<String, Double>> SumEmissionTable_denominator;
    private static HashMap<String, HashMap<String, Double>> SumTransmissionTable_numerator;
    private static HashMap<String, HashMap<String, Double>> SumTransmissionTable_denominator;
    private static HashMap<String, Double> SumInitializationTable_numerator;
    
    private static HashMap<String, HashMap<String, Double>> BeforeEmissionTable;
    private static HashMap<String, HashMap<String, Double>> BeforeTransmissionTable;
    private static HashMap<String, Double>  BeforeInitializationTable;
    
    private static HashMap<String, HashMap<String, Double>> AfterEmissionTable;
    private static HashMap<String, HashMap<String, Double>> AfterTransmissionTable;
    private static HashMap<String, Double>  AfterInitializationTable;
      
    public static void Initialization(){
        BeforeEmissionTable = new HashMap<>();
        HashMap<String, Double> C_row_b = new HashMap<>();
        C_row_b.put("that", (double)3/(double)8);
        C_row_b.put("is", (double)1/(double)8);
        C_row_b.put("not", (double)1/(double)8); 
        C_row_b.put("it", (double)1/(double)8); 
        C_row_b.put("good", (double)1/(double)8); 
        C_row_b.put("bad", (double)1/(double)8); 
        BeforeEmissionTable.put("C", C_row_b);
        HashMap<String, Double> N_row_b = new HashMap<>();
        N_row_b.put("that", (double)5/(double)14);
        N_row_b.put("is", (double)1/(double)14);
        N_row_b.put("not", (double)3/(double)14); 
        N_row_b.put("it", (double)3/(double)14); 
        N_row_b.put("good", (double)1/(double)14); 
        N_row_b.put("bad", (double)1/(double)14); 
        BeforeEmissionTable.put("N", N_row_b);
        HashMap<String, Double> V_row_b = new HashMap<>();
        V_row_b.put("that", (double)1/(double)12);
        V_row_b.put("is", (double)7/(double)12);
        V_row_b.put("not", (double)1/(double)12); 
        V_row_b.put("it", (double)1/(double)12); 
        V_row_b.put("good", (double)1/(double)12); 
        V_row_b.put("bad", (double)1/(double)12); 
        BeforeEmissionTable.put("V", V_row_b);
        HashMap<String, Double> J_row_b = new HashMap<>();
        J_row_b.put("that", (double)1/(double)7);
        J_row_b.put("is", (double)1/(double)7);
        J_row_b.put("not", (double)1/(double)7); 
        J_row_b.put("it", (double)1/(double)7); 
        J_row_b.put("good", (double)2/(double)7); 
        J_row_b.put("bad", (double)1/(double)7); 
        BeforeEmissionTable.put("J", J_row_b);
       
        BeforeTransmissionTable = new HashMap<>();
        HashMap<String, Double> pre_C_Row_b = new HashMap<>();
        pre_C_Row_b.put("C", (double)1/(double)6);
        pre_C_Row_b.put("N", (double)1/(double)2);
        pre_C_Row_b.put("V", (double)1/(double)6);
        pre_C_Row_b.put("J", (double)1/(double)6);
        BeforeTransmissionTable.put("C", pre_C_Row_b);
        HashMap<String, Double> pre_N_Row_b = new HashMap<>();
        pre_N_Row_b.put("C", (double)1/(double)10);
        pre_N_Row_b.put("N", (double)3/(double)10);
        pre_N_Row_b.put("V", (double)2/(double)5);
        pre_N_Row_b.put("J", (double)1/(double)5);
        BeforeTransmissionTable.put("N", pre_N_Row_b);
        HashMap<String, Double> pre_V_Row_b = new HashMap<>();
        pre_V_Row_b.put("C", (double)1/(double)9);
        pre_V_Row_b.put("N", (double)5/(double)9);
        pre_V_Row_b.put("V", (double)2/(double)9);
        pre_V_Row_b.put("J", (double)1/(double)9);
        BeforeTransmissionTable.put("V", pre_V_Row_b);
        HashMap<String, Double> pre_J_Row_b = new HashMap<>();
        pre_J_Row_b.put("C", (double)1/(double)4);
        pre_J_Row_b.put("N", (double)1/(double)4);
        pre_J_Row_b.put("V", (double)1/(double)4);
        pre_J_Row_b.put("J", (double)1/(double)4);
        BeforeTransmissionTable.put("J", pre_J_Row_b);
        
        BeforeInitializationTable = new HashMap<>();
        BeforeInitializationTable.put("C", (double)3/(double)8);
        BeforeInitializationTable.put("N", (double)1/(double)8);
        BeforeInitializationTable.put("V", (double)3/(double)8);
        BeforeInitializationTable.put("J", (double)1/(double)8);
        
        // emission table
        SumEmissionTable_numerator = new HashMap<>();
        HashMap<String, Double> C_row_n = new HashMap<>();
        C_row_n.put("that", (double) 0);
        C_row_n.put("is", (double) 0);
        C_row_n.put("not", (double) 0); 
        C_row_n.put("it", (double) 0); 
        C_row_n.put("good", (double) 0); 
        C_row_n.put("bad", (double) 0); 
        SumEmissionTable_numerator.put("C", C_row_n);
        HashMap<String, Double> N_row_n = new HashMap<>();
        N_row_n.put("that", (double) 0);
        N_row_n.put("is", (double) 0);
        N_row_n.put("not", (double) 0); 
        N_row_n.put("it", (double) 0); 
        N_row_n.put("good", (double) 0); 
        N_row_n.put("bad", (double) 0); 
        SumEmissionTable_numerator.put("N", N_row_n);
        HashMap<String, Double> V_row_n = new HashMap<>();
        V_row_n.put("that", (double) 0);
        V_row_n.put("is", (double) 0);
        V_row_n.put("not", (double) 0); 
        V_row_n.put("it", (double) 0); 
        V_row_n.put("good", (double) 0); 
        V_row_n.put("bad", (double) 0); 
        SumEmissionTable_numerator.put("V", V_row_n);
        HashMap<String, Double> J_row_n = new HashMap<>();
        J_row_n.put("that", (double) 0);
        J_row_n.put("is", (double) 0);
        J_row_n.put("not", (double) 0); 
        J_row_n.put("it", (double) 0); 
        J_row_n.put("good", (double) 0); 
        J_row_n.put("bad", (double) 0); 
        SumEmissionTable_numerator.put("J", J_row_n);
        
        SumEmissionTable_denominator = new HashMap<>();
        HashMap<String, Double> C_row_d = new HashMap<>();
        C_row_d.put("that", (double) 0);
        C_row_d.put("is", (double) 0);
        C_row_d.put("not", (double) 0); 
        C_row_d.put("it", (double) 0); 
        C_row_d.put("good", (double) 0); 
        C_row_d.put("bad", (double) 0); 
        SumEmissionTable_denominator.put("C", C_row_d);
        HashMap<String, Double> N_row_d = new HashMap<>();
        N_row_d.put("that", (double) 0);
        N_row_d.put("is", (double) 0);
        N_row_d.put("not", (double) 0); 
        N_row_d.put("it", (double) 0); 
        N_row_d.put("good", (double) 0); 
        N_row_d.put("bad", (double) 0); 
        SumEmissionTable_denominator.put("N", N_row_d);
        HashMap<String, Double> V_row_d = new HashMap<>();
        V_row_d.put("that", (double) 0);
        V_row_d.put("is", (double) 0);
        V_row_d.put("not", (double) 0); 
        V_row_d.put("it", (double) 0); 
        V_row_d.put("good", (double) 0); 
        V_row_d.put("bad", (double) 0); 
        SumEmissionTable_denominator.put("V", V_row_d);
        HashMap<String, Double> J_row_d = new HashMap<>();
        J_row_d.put("that", (double) 0);
        J_row_d.put("is", (double) 0);
        J_row_d.put("not", (double) 0); 
        J_row_d.put("it", (double) 0); 
        J_row_d.put("good", (double) 0); 
        J_row_d.put("bad", (double) 0); 
        SumEmissionTable_denominator.put("J", J_row_d);
        
        AfterEmissionTable = new HashMap<>();
        HashMap<String, Double> C_row_a = new HashMap<>();
        C_row_a.put("that", (double) 0);
        C_row_a.put("is", (double) 0);
        C_row_a.put("not", (double) 0); 
        C_row_a.put("it", (double) 0); 
        C_row_a.put("good", (double) 0); 
        C_row_a.put("bad", (double) 0); 
        AfterEmissionTable.put("C", C_row_a);
        HashMap<String, Double> N_row_a = new HashMap<>();
        N_row_a.put("that", (double) 0);
        N_row_a.put("is", (double) 0);
        N_row_a.put("not", (double) 0); 
        N_row_a.put("it", (double) 0); 
        N_row_a.put("good", (double) 0); 
        N_row_a.put("bad", (double) 0); 
        AfterEmissionTable.put("N", N_row_a);
        HashMap<String, Double> V_row_a = new HashMap<>();
        V_row_a.put("that", (double) 0);
        V_row_a.put("is", (double) 0);
        V_row_a.put("not", (double) 0); 
        V_row_a.put("it", (double) 0); 
        V_row_a.put("good", (double) 0); 
        V_row_a.put("bad", (double) 0); 
        AfterEmissionTable.put("V", V_row_a);
        HashMap<String, Double> J_row_a = new HashMap<>();
        J_row_a.put("that", (double) 0);
        J_row_a.put("is", (double) 0);
        J_row_a.put("not", (double) 0); 
        J_row_a.put("it", (double) 0); 
        J_row_a.put("good", (double) 0); 
        J_row_a.put("bad", (double) 0); 
        AfterEmissionTable.put("J", J_row_a);
       
        SumTransmissionTable_numerator  = new HashMap<>();
        HashMap<String, Double> pre_C_Row_n = new HashMap<>();
        pre_C_Row_n.put("C", (double) 0);
        pre_C_Row_n.put("N", (double) 0);
        pre_C_Row_n.put("V", (double) 0);
        pre_C_Row_n.put("J", (double) 0);
        SumTransmissionTable_numerator.put("C", pre_C_Row_n);
        HashMap<String, Double> pre_N_Row_n = new HashMap<>();
        pre_N_Row_n.put("C", (double) 0);
        pre_N_Row_n.put("N", (double) 0);
        pre_N_Row_n.put("V", (double) 0);
        pre_N_Row_n.put("J", (double) 0);
        SumTransmissionTable_numerator.put("N", pre_N_Row_n);
        HashMap<String, Double> pre_V_Row_n = new HashMap<>();
        pre_V_Row_n.put("C", (double) 0);
        pre_V_Row_n.put("N", (double) 0);
        pre_V_Row_n.put("V", (double) 0);
        pre_V_Row_n.put("J", (double) 0);
        SumTransmissionTable_numerator.put("V", pre_V_Row_n);
        HashMap<String, Double> pre_J_Row_n = new HashMap<>();
        pre_J_Row_n.put("C", (double) 0);
        pre_J_Row_n.put("N", (double) 0);
        pre_J_Row_n.put("V", (double) 0);
        pre_J_Row_n.put("J", (double) 0);
        SumTransmissionTable_numerator.put("J", pre_J_Row_n);
        
        SumTransmissionTable_denominator = new HashMap<>();
        HashMap<String, Double> pre_C_Row_d = new HashMap<>();
        pre_C_Row_d.put("C", (double) 0);
        pre_C_Row_d.put("N", (double) 0);
        pre_C_Row_d.put("V", (double) 0);
        pre_C_Row_d.put("J", (double) 0);
        SumTransmissionTable_denominator.put("C", pre_C_Row_d);
        HashMap<String, Double> pre_N_Row_d = new HashMap<>();
        pre_N_Row_d.put("C", (double) 0);
        pre_N_Row_d.put("N", (double) 0);
        pre_N_Row_d.put("V", (double) 0);
        pre_N_Row_d.put("J", (double) 0);
        SumTransmissionTable_denominator.put("N", pre_N_Row_d);
        HashMap<String, Double> pre_V_Row_d = new HashMap<>();
        pre_V_Row_d.put("C", (double) 0);
        pre_V_Row_d.put("N", (double) 0);
        pre_V_Row_d.put("V", (double) 0);
        pre_V_Row_d.put("J", (double) 0);
        SumTransmissionTable_denominator.put("V", pre_V_Row_d);
        HashMap<String, Double> pre_J_Row_d = new HashMap<>();
        pre_J_Row_d.put("C", (double) 0);
        pre_J_Row_d.put("N", (double) 0);
        pre_J_Row_d.put("V", (double) 0);
        pre_J_Row_d.put("J", (double) 0);
        SumTransmissionTable_denominator.put("J", pre_J_Row_d);
        
        AfterTransmissionTable  = new HashMap<>();
        HashMap<String, Double> pre_C_Row_a = new HashMap<>();
        pre_C_Row_a.put("C", (double) 0);
        pre_C_Row_a.put("N", (double) 0);
        pre_C_Row_a.put("V", (double) 0);
        pre_C_Row_a.put("J", (double) 0);
        AfterTransmissionTable.put("C", pre_C_Row_a);
        HashMap<String, Double> pre_N_Row_a = new HashMap<>();
        pre_N_Row_a.put("C", (double) 0);
        pre_N_Row_a.put("N", (double) 0);
        pre_N_Row_a.put("V", (double) 0);
        pre_N_Row_a.put("J", (double) 0);
        AfterTransmissionTable.put("N", pre_N_Row_a);
        HashMap<String, Double> pre_V_Row_a = new HashMap<>();
        pre_V_Row_a.put("C", (double) 0);
        pre_V_Row_a.put("N", (double) 0);
        pre_V_Row_a.put("V", (double) 0);
        pre_V_Row_a.put("J", (double) 0);
        AfterTransmissionTable.put("V", pre_V_Row_a);
        HashMap<String, Double> pre_J_Row_a = new HashMap<>();
        pre_J_Row_a.put("C", (double) 0);
        pre_J_Row_a.put("N", (double) 0);
        pre_J_Row_a.put("V", (double) 0);
        pre_J_Row_a.put("J", (double) 0);
        AfterTransmissionTable.put("J", pre_J_Row_a);
        
        SumInitializationTable_numerator = new HashMap<>();
        SumInitializationTable_numerator.put("C", (double) 0);
        SumInitializationTable_numerator.put("N", (double) 0);
        SumInitializationTable_numerator.put("V", (double) 0);
        SumInitializationTable_numerator.put("J", (double) 0);
        
        AfterInitializationTable = new HashMap<>();
        AfterInitializationTable.put("C", (double) 0);
        AfterInitializationTable.put("N", (double) 0);
        AfterInitializationTable.put("V", (double) 0);
        AfterInitializationTable.put("J", (double) 0);
    }
    
    public static void main(String[] args) throws Exception{
        int maxLen = 6;
        List<String> sentence_list = new ArrayList<String>();
//        sentence_list.add("that that is is");
//        sentence_list.add("that that is not is not");
//        sentence_list.add("is that it");
//        sentence_list.add("is it that good");
        sentence_list.add("bad is not good");
        sentence_list.add("is it bad");
        
        Initialization();  
        
        for (String sentence : sentence_list) {
            String[] input_words = sentence.split("\\s+");
            
            System.out.println((char)27 + "[31m" + "Sentence: " + sentence);
            
            ForwardBackward OneSentence = 
                    new ForwardBackward(sentence, 
                            BeforeInitializationTable, BeforeTransmissionTable, BeforeEmissionTable);
            OneSentence.OneRound();
           
            // initialization table
//            SumInitializationTable_numerator = new HashMap<>();
            SumInitializationTable_numerator.put("C", OneSentence.GetGammaTable().get("C")[0] + SumInitializationTable_numerator.get("C"));
            SumInitializationTable_numerator.put("N", OneSentence.GetGammaTable().get("N")[0] + SumInitializationTable_numerator.get("N"));
            SumInitializationTable_numerator.put("V", OneSentence.GetGammaTable().get("V")[0] + SumInitializationTable_numerator.get("V"));
            SumInitializationTable_numerator.put("J", OneSentence.GetGammaTable().get("J")[0] + SumInitializationTable_numerator.get("J"));
             
//            SumInitializationTable = new HashMap<>();
            AfterInitializationTable.put("C", SumInitializationTable_numerator.get("C")/2);
            AfterInitializationTable.put("N", SumInitializationTable_numerator.get("N")/2);
            AfterInitializationTable.put("V", SumInitializationTable_numerator.get("V")/2);
            AfterInitializationTable.put("J", SumInitializationTable_numerator.get("J")/2);
             
            // transmission table
            int sentenceLen = input_words.length;
//            SumTransmissionTable_numerator = new HashMap<>(); 
//            SumTransmissionTable_denominator = new HashMap<>(); 
            for (String state_i: OneSentence.GetTransmissionTable().keySet()) {
                HashMap<String, Double> pre_state_Row_numerator = new HashMap<>();
                HashMap<String, Double> pre_state_Row_denominator = new HashMap<>();
                double gamma_i = SumTransmissionTable_denominator.get(state_i).get(state_i);
                for (int i=0; i < sentenceLen-1; i++){
                    gamma_i += OneSentence.GetGammaTable().get(state_i)[i];
                }

                for(String state_j: SumTransmissionTable_numerator.get(state_i).keySet()) {
                    double epsilon_ij = SumTransmissionTable_numerator.get(state_i).get(state_j);
                    for (int i=0; i < sentenceLen-1; i++){
                        epsilon_ij += OneSentence.GetEpsilonTable().get(state_i).get(state_j)[i];
                    }
                    pre_state_Row_numerator.put(state_j, epsilon_ij);
                    pre_state_Row_denominator.put(state_j, gamma_i);
                }
                
                SumTransmissionTable_numerator.put(state_i, pre_state_Row_numerator);
                SumTransmissionTable_denominator.put(state_i, pre_state_Row_denominator);
            }
            
            for (String state_i: AfterTransmissionTable.keySet()) {
                HashMap<String, Double> pre_state_Row = new HashMap<>();
                for(String state_j: AfterTransmissionTable.get(state_i).keySet()) {
                    pre_state_Row.put(state_j, 
                            SumTransmissionTable_numerator.get(state_i).get(state_j)
                                    /SumTransmissionTable_denominator.get(state_i).get(state_j));
                }
                AfterTransmissionTable.put(state_i, pre_state_Row);
            }
        
        
            // emission table
//            SumEmissionTable_numerator = new HashMap<>();
//            SumEmissionTable_denominator = new HashMap<>();
            for(String state: OneSentence.GetEmissionTable().keySet()) {
                HashMap<String, Double> state_row_numerator = new HashMap<>();
                HashMap<String, Double> state_row_denominator = new HashMap<>();
                double gamma_i = SumEmissionTable_denominator.get(state).get(input_words[0]);
                for (int i=0; i < sentenceLen; i++){
                    gamma_i += OneSentence.GetGammaTable().get(state)[i];
                }
                for(String word: OneSentence.GetEmissionTable().get(state).keySet()) {
                    double gamma_i_word = SumEmissionTable_numerator.get(state).get(word);
                    for (int i=0; i < sentenceLen; i++){ 
                        if (word.equals(input_words[i])) {
                            gamma_i_word += OneSentence.GetGammaTable().get(state)[i];
                        }
                    }
                    state_row_numerator.put(word, gamma_i_word);
                    state_row_denominator.put(word, gamma_i);
                }
                SumEmissionTable_numerator.put(state, state_row_numerator);
                SumEmissionTable_denominator.put(state, state_row_denominator);
            }
            
            for (String state_i: AfterEmissionTable.keySet()) {
                HashMap<String, Double> state_Row = new HashMap<>();
                for(String word: AfterEmissionTable.get(state_i).keySet()) {
                    state_Row.put(word, 
                            SumEmissionTable_numerator.get(state_i).get(word)
                                    /SumEmissionTable_denominator.get(state_i).get(word));
                }
                AfterEmissionTable.put(state_i, state_Row);
            }  
        }
        
        // print after
        System.out.println("----- print non-normalized result -----");
        System.out.println("Print Initialization Table");
        Print1DHash(AfterInitializationTable);
        System.out.println("\n");

        System.out.println("Print Transmission Table");
        Print2DHash(AfterTransmissionTable); 
        System.out.println("\n");

        System.out.println("Print Emission Table");
        Print2DHash(AfterEmissionTable);
        System.out.println("\n");
        
        // sum with before
        for (String state: BeforeInitializationTable.keySet()) {
            AfterInitializationTable.put(state, BeforeInitializationTable.get(state) + 0.5 * AfterInitializationTable.get(state));
        }
        
        for (String state_i: BeforeTransmissionTable.keySet()) {
            HashMap<String, Double> state_Row = new HashMap<>();
            for(String state_j: BeforeTransmissionTable.get(state_i).keySet()) {
                state_Row.put(state_j, BeforeTransmissionTable.get(state_i).get(state_j) + 0.5 * AfterTransmissionTable.get(state_i).get(state_j));
            }
            AfterTransmissionTable.put(state_i, state_Row);
        }
        
        for (String state: BeforeEmissionTable.keySet()) {
            HashMap<String, Double> state_Row = new HashMap<>();
            for(String word: BeforeEmissionTable.get(state).keySet()) {
                state_Row.put(word, BeforeEmissionTable.get(state).get(word) + 0.5 * AfterEmissionTable.get(state).get(word));
            }
            AfterEmissionTable.put(state, state_Row);
        }
        
        // normalize
        // get sum
        double state_sum = 0;
        for (Double prob : AfterInitializationTable.values()) {
            state_sum += prob;
        }
        for (String state: AfterInitializationTable.keySet()) {
            AfterInitializationTable.put(state, AfterInitializationTable.get(state)/state_sum);
        }
        
        for (String state_i: AfterTransmissionTable.keySet()) {
            HashMap<String, Double> state_Row = new HashMap<>();
            state_sum = 0;
            for(Double prob: AfterTransmissionTable.get(state_i).values()) {
                state_sum += prob;
            }
            
            for(String state_j: AfterTransmissionTable.get(state_i).keySet()) {
                state_Row.put(state_j, AfterTransmissionTable.get(state_i).get(state_j)/state_sum);
            }
            AfterTransmissionTable.put(state_i, state_Row);
        }
        
        for (String state: AfterEmissionTable.keySet()) {
            HashMap<String, Double> state_Row = new HashMap<>();
            state_sum = 0;
            for(Double prob: AfterEmissionTable.get(state).values()) {
                state_sum += prob;
            }
            
            for(String word: AfterEmissionTable.get(state).keySet()) {
                state_Row.put(word, AfterEmissionTable.get(state).get(word)/state_sum);
            }
            AfterEmissionTable.put(state, state_Row);
        }
        
        
        // print after
        System.out.println("----- print normalized result -----");
        System.out.println("Print Initialization Table");
        Print1DHash(AfterInitializationTable);
        System.out.println("\n");

        System.out.println("Print Transmission Table");
        Print2DHash(AfterTransmissionTable); 
        System.out.println("\n");

        System.out.println("Print Emission Table");
        Print2DHash(AfterEmissionTable);
        System.out.println("\n"); 
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
}
