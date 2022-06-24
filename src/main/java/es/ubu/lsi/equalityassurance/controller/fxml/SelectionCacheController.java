package es.ubu.lsi.equalityassurance.controller.fxml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.util.I18n;
import es.ubu.lsi.equalityassurance.util.Serialization;
import es.ubu.lsi.equalityassurance.util.UtilAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class SelectionCacheController {

	@FXML
	private ListView<File> listViewCaches;

	private File courseDir;

	private DataBase dataBase;
	private MainController mainController;

	public void init(MainController mainController) {
		this.mainController = mainController;
		setCellFactory();
		onSelectionCache();
	}

	private void setCellFactory() {
		listViewCaches.setCellFactory(callback -> new ListCell<File>() {
			@Override
			public void updateItem(File file, boolean empty) {
				super.updateItem(file, empty);
				if (empty || file == null) {
					setText(null);
					setContextMenu(null);
				} else {
					LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()),
							ZoneId.systemDefault());

					setText(localDateTime.format(Controller.DATE_TIME_FORMATTER));
					ContextMenu menu = new ContextMenu();
					MenuItem delete = new MenuItem(I18n.get("menu.delete"));
					delete.setOnAction(e -> deleteFile(file));
					menu.getItems()
							.addAll(delete);
					setContextMenu(menu);
				}
			}

		});
	}

	private void deleteFile(File file) {
		try {
			Files.delete(file.toPath());
			updateListView();
		} catch (IOException e) {
			log.warn("No se ha podido borrar", e);
		}

	}

	public void updateListView(File courseDir) {
		this.courseDir = courseDir;
		File[] files = courseDir.listFiles();
		if (files != null) {
			ObservableList<File> observableListFiles = FXCollections.observableArrayList(files);
			Collections.reverse(observableListFiles);
			listViewCaches.setItems(observableListFiles);

		} else {
			listViewCaches.setItems(FXCollections.emptyObservableList());
		}
	}

	public void updateListView() {
		updateListView(courseDir);

	}

	private void onSelectionCache() {
		listViewCaches.getSelectionModel()
				.selectedItemProperty()
				.addListener((ov, old, newValue) -> {
					if (newValue == null) {
						dataBase = null;
					} else {
						try {
							DataBase dataBaseSelected = (DataBase) Serialization.decrypt(Controller.getInstance()
									.getPassword(), newValue.toString());
							this.dataBase = dataBaseSelected;

						} catch (Exception e) {
							UtilAlert.errorWindow("Error al deserializar", e);
							return;
						}
					}
					mainController.getWebViewContentController()
							.update(dataBase);

				});

	}

}
