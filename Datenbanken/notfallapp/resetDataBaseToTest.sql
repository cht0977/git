DELETE FROM map where map_id > 0;
ALTER TABLE map AUTO_INCREMENT=1;
ALTER TABLE wifi AUTO_INCREMENT=1;
ALTER TABLE room AUTO_INCREMENT=1;
ALTER TABLE position AUTO_INCREMENT=1;
ALTER TABLE poi AUTO_INCREMENT=1;

INSERT INTO map (map_name)
VALUES
("KarteNummer1"),
("KarteNummer2"),
("KarteNummer3"),
("KarteNummer4")

INSERT INTO position (map_id, x, y)
VALUES
(1, 1, 1),
(1, 23, 40),
(2, 2, 25),
(3, 23, 2),
(4, 3, 27)

INSERT INTO room (room_name, map_id)
VALUES
("kleinesRäumchen", 1),
("RaumderZweite", 1),
("Raum3", 2),
("Raum4", 3),
("Raum5", 4)

INSERT INTO poi (map_id, x, y, img_src)
VALUES
(1, 25, 35, "koffer"),
(1, 23, 45, "ersthelfer"),
(2, 82, 4, "room"),
(2, 44, 2, "sammelpl"),
(3, 102, 34, "defi"),
(4, 3, 62, "feuerloescher")

INSERT INTO wifi (mac_adress, lvl, pos_id)
VALUES
("00:00:00:00:00:00", -90, 1),
("00:00:00:00:00:01", -89, 1),
("00:00:00:00:00:02", -88, 2),
("00:00:00:00:00:03", -87, 3),
("00:00:00:00:00:04", -86, 4)
