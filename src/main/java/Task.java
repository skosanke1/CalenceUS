import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task {
	/**
	 * Saves date, tag name, and zip url from the GitHub API releases JsonNode to a .csv file
	 * @param root
	 * @return 1 if successful, 0 if IOException occurs
	 */
	public static int writeData(JsonNode root) throws IOException {
		try {
			//Initialize the .csv file and headers
			FileWriter writer = new FileWriter("data.csv");
			writer.append("created_date,tag_name,url");
			writer.append("\n");

			//Iterate through root and write data to file
			for (JsonNode releaseNode : root) {
				if (releaseNode.get("target_commitish").asText().equals("main")) {
					writer.append(releaseNode.get("published_at").asText().substring(0,10) + ","); //created_date
					writer.append(releaseNode.get("tag_name").asText() + ","); //tag_name
					writer.append(releaseNode.get("assets").get(0).get("browser_download_url").asText() + ","); //url
					writer.append("\n");
				}
			}
			writer.close();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String owner = "twbs"; //repo owner name
		String repository = "bootstrap"; //repository name
		String uri = String.format("https://api.github.com/repos/%s/%s/releases", owner, repository);

		//Create a Http client to send request
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
		HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

		//Convert JSON using ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(response.body());

		//Write data to file
		if (writeData(rootNode) != 1) {
			System.out.println("An error occured while writing the data.");
		}
	}
}
