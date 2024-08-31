import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.MultinomialModelPrediction;

public class Main {
    private static String modelClassName = "gbm_dd9bd069_80f0_4e2e_bca8_3539c0818e3a";

    public static void main(String[] args) {
        try {
            // Load the model
            hex.genmodel.GenModel rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
            EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);

            // Prepare input data
            RowData row = new RowData();
            //row.put("CURRENT", "1");
            //row.put("I/O", "1");
            //row.put("CRC", "1");
           // row.put("MONDATA", "1");
            //row.put("SONDATA", "1");
            //row.put("LIMIT", "0");
            //row.put("AMSRBS", "0");
            //row.put("M/A", "1");

            // Predict
            MultinomialModelPrediction prediction = model.predictMultinomial(row);

            // Process the prediction response
            System.out.println("Predicted Label: " + prediction.label);

            // Print class probabilities
            System.out.println("Class probabilities:");
            for (int i = 0; i < prediction.classProbabilities.length; i++) {
                System.out.printf("Class %d: %.4f\n", i, prediction.classProbabilities[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during model prediction.");
        }
    }
}
