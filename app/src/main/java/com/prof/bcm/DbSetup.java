package com.prof.bcm;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbSetup {

    public static final String TAG = "ContributionDB";
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT_CONTRIBUTED = "amountContributed";
    public static final Double DEFAULT_AMOUNT = 0.00;
    public static final String MONTHLY_CONTRIBUTIONS_COLLECTION = "Monthly Contributions";
    String month = "December";
    private FirebaseFirestore db;
    private List<String> months = new ArrayList<>();
    private List<ArrayList> allBacentasList = new ArrayList<>();


    public DbSetup() {
        db = FirebaseFirestore.getInstance();
        setMonths();
        setBacentas();
        setIndividualData();
    }

    private void setMonths() {
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    private void setBacentas() {

        ArrayList<String> campusBacentas = new ArrayList<>();
        campusBacentas.add("Getfund A");
        campusBacentas.add("Getfund B");
        campusBacentas.add("Sir Joe");
        campusBacentas.add("Plantain");
        campusBacentas.add("Eli Joe");

        ArrayList<String> junctionBacentas = new ArrayList<>();
        junctionBacentas.add("Junction 1 Up");
        junctionBacentas.add("Junction 1 down");
        junctionBacentas.add("Junction 4 Up");
        junctionBacentas.add("Junction 4 Down");
        junctionBacentas.add("Junction 6");

        ArrayList<String> uniBacentas = new ArrayList<>();
        uniBacentas.add("NMTC");

        ArrayList<String> mflf = new ArrayList<>();
        mflf.add("Kotech");
        mflf.add("Hunsec");
        mflf.add("Omese");

        ArrayList<String> townBacentas = new ArrayList<>();
        townBacentas.add("Betom");
        townBacentas.add("Effiduase");
        townBacentas.add("Oyoko");

        allBacentasList = new ArrayList<>();
        allBacentasList.add(campusBacentas);
        allBacentasList.add(junctionBacentas);
        allBacentasList.add(uniBacentas);
        allBacentasList.add(mflf);
        allBacentasList.add(townBacentas);
    }


    public void createContributionsPerMonth() {

        for (int i = 0; i < allBacentasList.size(); i++) {

            for (int j = 0; j < allBacentasList.get(i).size(); j++) {

                switch (allBacentasList.get(i).get(j).toString()){

                    case "Getfund A":
                        for (int k = 0; k < getfund_a_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION)
                                    .document(month)
                                    .collection(allBacentasList.get(i).get(j).toString())
                                    .document()
                                    .set(getfund_a_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;
                    case "Getfund B":
                        for (int k = 0; k < getfund_b_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(getfund_b_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Sir Joe":
                        for (int k = 0; k < sir_joe_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(sir_joe_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Plantain":
                        for (int k = 0; k < plantain_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(plantain_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Eli Joe":
                        for (int k = 0; k < eli_joe_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(eli_joe_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Junction 1 Up":
                        for (int k = 0; k < junction_1_up_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(junction_1_up_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Junction 1 down":
                        for (int k = 0; k < junction_1_down_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(junction_1_down_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Junction 4 Up":
                        for (int k = 0; k < junction_4_up_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(junction_4_up_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Junction 4 Down":
                        for (int k = 0; k < junction_4_down_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(junction_4_down_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Junction 6":
                        for (int k = 0; k < junction_6_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(junction_6_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "NMTC":
                        for (int k = 0; k < nmtc_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(nmtc_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Kotech":
                        for (int k = 0; k < kotech_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(kotech_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Hunsec":
                        for (int k = 0; k < hunsec_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(hunsec_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Omese":
                        for (int k = 0; k < omese_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(omese_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Betom":
                        for (int k = 0; k < betom_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(betom_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Effiduase":
                        for (int k = 0; k < effiduase_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(effiduase_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;

                    case "Oyoko":
                        for (int k = 0; k < oyoko_shepherds.size(); k++) {
                            db.collection(MONTHLY_CONTRIBUTIONS_COLLECTION).document(month)
                                    .collection(allBacentasList.get(i).get(j).toString()).document()
                                    .set(oyoko_shepherds.get(k));//loop through the number of shepherds that bacenta has and create them
                        }
                        break;
                }
            }
        }
    }

    private ArrayList<String> constituencyList;
    private void createConstituencies(){
        db = FirebaseFirestore.getInstance();
        constituencyList = new ArrayList<>();
        constituencyList.add("Campus");
        constituencyList.add("Junctions");
        constituencyList.add("Uni");
        constituencyList.add("MFLF");
        constituencyList.add("Town");

        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < constituencyList.size(); i++) {
            data.clear();
            data.put("name", constituencyList.get(i));
            db.collection("Constituencies").document().set(data);
        }
    }

    public void createBacentas(){

        createConstituencies();
        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < allBacentasList.size(); i++) {
            for (int j = 0; j < allBacentasList.get(i).size(); j++) {
                data.clear();
                data.put("name", allBacentasList.get(i).get(j).toString());
                db.collection(constituencyList.get(i)).document().set(data);
            }
        }
    }

    //Getfund A individual data holder
    Map<String, String> chris = new HashMap<>();
    Map<String, String> john = new HashMap<>();

    ArrayList<Map> getfund_a_shepherds = new ArrayList<>();

    //Getfund B individual data holder
    Map<String, String> adelaide = new HashMap<>();

    ArrayList<Map> getfund_b_shepherds = new ArrayList<>();


    //Sir Joe individual data holder
    Map<String, String> anita = new HashMap<>();

    ArrayList<Map> sir_joe_shepherds = new ArrayList<>();


    //Plantain individual data holder
    Map<String, String> harrison = new HashMap<>();

    ArrayList<Map> plantain_shepherds = new ArrayList<>();


    //Eli Joe individual data holder
    Map<String, String> bassem = new HashMap<>();

    ArrayList<Map> eli_joe_shepherds = new ArrayList<>();


    //JUNCTIONS
    //Junction 1 up individual data holder
    Map<String, String> godfred = new HashMap<>();

    ArrayList<Map> junction_1_up_shepherds = new ArrayList<>();


    //Junction 1 down individual data holder
    Map<String, String> renia = new HashMap<>();

    ArrayList<Map> junction_1_down_shepherds = new ArrayList<>();


    //Junction 4 up individual data holder
    Map<String, String> eric = new HashMap<>();
    Map<String, String> sheila = new HashMap<>();

    ArrayList<Map> junction_4_up_shepherds = new ArrayList<>();


    //Junction 4 down individual data holder
    Map<String, String> kobby = new HashMap<>();

    ArrayList<Map> junction_4_down_shepherds = new ArrayList<>();


    //Junction 6 individual data holder
    Map<String, String> joshua = new HashMap<>();

    ArrayList<Map> junction_6_shepherds = new ArrayList<>();


    //UNI
    //NMTC individual data holder
    Map<String, String> esther = new HashMap<>();

    ArrayList<Map> nmtc_shepherds = new ArrayList<>();


    //TOWN
    //Betom individual data holder
    Map<String, String> keziah = new HashMap<>();

    ArrayList<Map> betom_shepherds = new ArrayList<>();


    //Oyoko individual data holder
    Map<String, String> prosper = new HashMap<>();
    Map<String, String> manfred = new HashMap<>();

    ArrayList<Map> oyoko_shepherds = new ArrayList<>();


    //Effiduase individual data holder
    Map<String, String> jacob = new HashMap<>();
    Map<String, String> eric_effiduase = new HashMap<>();

    ArrayList<Map> effiduase_shepherds = new ArrayList<>();


    //MFLF
    //Kotech individual data holder
    Map<String, String> dankwah = new HashMap<>();
    Map<String, String> lex = new HashMap<>();
    Map<String, String> maame_abena = new HashMap<>();

    ArrayList<Map> kotech_shepherds = new ArrayList<>();


    //Hunsec individual data holder
    Map<String, String> ella = new HashMap<>();
    Map<String, String> fidel = new HashMap<>();

    ArrayList<Map> hunsec_shepherds = new ArrayList<>();


    //Omese individual data holder
    Map<String, String> miguel = new HashMap<>();
    Map<String, String> abigail = new HashMap<>();
    Map<String, String> celestine = new HashMap<>();

    ArrayList<Map> omese_shepherds = new ArrayList<>();


    private void setIndividualData() {
        //CAMPUS
        //individual data for Gefund A Bacenta
        chris.put(KEY_NAME, "Christian");
        john.put(KEY_NAME, "John");

        getfund_a_shepherds.add(chris);
        getfund_a_shepherds.add(john);

        for (int i = 0; i < getfund_a_shepherds.size(); i++) {
            getfund_a_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for Gefund B Bacenta
        adelaide.put(KEY_NAME, "Adelaide Asmah");

        getfund_b_shepherds.add(adelaide);

        for (int i = 0; i < getfund_b_shepherds.size(); i++) {
            getfund_b_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for sir joe Bacenta
        anita.put(KEY_NAME, "Anita Doe");

        sir_joe_shepherds.add(anita);

        for (int i = 0; i < sir_joe_shepherds.size(); i++) {
            sir_joe_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for plantain Bacenta
        harrison.put(KEY_NAME, "Pastor Harrison");

        plantain_shepherds.add(harrison);

        for (int i = 0; i < plantain_shepherds.size(); i++) {
            plantain_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for elijoe Bacenta
        bassem.put(KEY_NAME, "Pastor Harrison");

        eli_joe_shepherds.add(bassem);

        for (int i = 0; i < eli_joe_shepherds.size(); i++) {
            eli_joe_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //JUNCTIONS
        //individual data for junction 1 up Bacenta
        godfred.put(KEY_NAME, "Godfred Yeboah");

        junction_1_up_shepherds.add(godfred);

        for (int i = 0; i < junction_1_up_shepherds.size(); i++) {
            junction_1_up_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for junction 1 down Bacenta
        renia.put(KEY_NAME, "Renia");

        junction_1_down_shepherds.add(renia);

        for (int i = 0; i < junction_1_down_shepherds.size(); i++) {
            junction_1_down_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for junction 4 up Bacenta
        eric.put(KEY_NAME, "Eric Akorli");
        sheila.put(KEY_NAME, "Sheila Asare");

        junction_4_up_shepherds.add(eric);
        junction_4_up_shepherds.add(sheila);

        for (int i = 0; i < junction_4_up_shepherds.size(); i++) {
            junction_4_up_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for junction 4 down Bacenta
        kobby.put(KEY_NAME, "Godfred Kobby");

        junction_4_down_shepherds.add(kobby);

        for (int i = 0; i < junction_4_down_shepherds.size(); i++) {
            junction_4_down_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for junction 6 Bacenta
        joshua.put(KEY_NAME, "Joshua Armah");

        junction_6_shepherds.add(joshua);

        for (int i = 0; i < junction_6_shepherds.size(); i++) {
            junction_6_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //UNI
        //individual data for nmtc Bacenta
        esther.put(KEY_NAME, "LP Esther");

        nmtc_shepherds.add(esther);

        for (int i = 0; i < nmtc_shepherds.size(); i++) {
            nmtc_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //TOWN
        //individual data for betom Bacenta
        keziah.put(KEY_NAME, "Keziah");

        betom_shepherds.add(keziah);

        for (int i = 0; i < betom_shepherds.size(); i++) {
            betom_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for oyoko Bacenta
        prosper.put(KEY_NAME, "Prosper Akorli");
        manfred.put(KEY_NAME, "Manfred Amoako");

        oyoko_shepherds.add(prosper);

        for (int i = 0; i < oyoko_shepherds.size(); i++) {
            oyoko_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for effiduase Bacenta
        jacob.put(KEY_NAME, "Jacob");
        eric_effiduase.put(KEY_NAME, "Eric Danso");

        effiduase_shepherds.add(jacob);
        effiduase_shepherds.add(eric_effiduase);

        for (int i = 0; i < effiduase_shepherds.size(); i++) {
            effiduase_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //MFLF
        //individual data for kotech Bacenta
        dankwah.put(KEY_NAME, "Dankwah Owusu");
        lex.put(KEY_NAME, "Evans Boakye (Lex)");
        maame_abena.put(KEY_NAME, "Maame Abena Agyekum");

        kotech_shepherds.add(dankwah);
        kotech_shepherds.add(lex);
        kotech_shepherds.add(maame_abena);

        for (int i = 0; i < kotech_shepherds.size(); i++) {
            kotech_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for Hunsec Bacenta
        ella.put(KEY_NAME, "Emmanuella Boadzie");
        fidel.put(KEY_NAME, "Fidel Elias");

        hunsec_shepherds.add(ella);
        hunsec_shepherds.add(fidel);

        for (int i = 0; i < hunsec_shepherds.size(); i++) {
            hunsec_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }


        //individual data for omese Bacenta
        miguel.put(KEY_NAME, "Miguel Augustus");
        celestine.put(KEY_NAME, "Celestine Fianu");
        abigail.put(KEY_NAME, "Abigail Amankwah");

        omese_shepherds.add(miguel);
        omese_shepherds.add(celestine);
        omese_shepherds.add(abigail);

        for (int i = 0; i < omese_shepherds.size(); i++) {
            omese_shepherds.get(i).put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
        }

    }



/*
    public void update() {


        db.collection("Months").document(month).collection(allBacentasList.get(0)).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getDocument() != null) {
                        String ID = dc.getDocument().getId();
                        HashMap<String, String> data = new HashMap<>();
                        data.put(KEY_AMOUNT_CONTRIBUTED, DEFAULT_AMOUNT);
                        db.collection("Months").document(month).collection(allBacentasList.get(0)).document(ID).set(data);

                    }
                }
            }
        });
    }

 */
}
