package es.ubu.lsi.equalityassurance.controller.fxml;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;

import es.ubu.lsi.equalityassurance.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Getter;

@Getter
public class SelectionCacheController {

	@FXML
	private ListView<File> listViewCaches;

	public void init(MainController mainController) {
		listViewCaches.setCellFactory(callback -> new ListCell<File>() {
			@Override
			public void updateItem(File file, boolean empty) {
				super.updateItem(file, empty);
				if (empty || file == null) {
					setText(null);
				} else {
					LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()),
							ZoneId.systemDefault());

					setText(file.getName() + " - " + localDateTime.format(Controller.DATE_TIME_FORMATTER));
				}
			}
		});
	}

	public void updateListView(File courseDir) {
		File[] files = courseDir.listFiles();
		if (files != null) {
			ObservableList<File> observableListFiles = FXCollections.observableArrayList(files);
			observableListFiles.sorted(Comparator.comparing(File::lastModified)
					.reversed());
			listViewCaches.setItems(observableListFiles);
		} else {
			listViewCaches.setItems(FXCollections.emptyObservableList());
		}
	}

}
