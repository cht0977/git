CREATE TABLE wifi  
(  
    wifi_id INTEGER PRIMARY KEY AUTO_INCREMENT,     
    mac_adress VARCHAR(45) NOT NULL,     
    lvl INTEGER NOT NULL,     
    pos_id INTEGER NOT NULL,     
    FOREIGN KEY (pos_id) 
    REFERENCES position (pos_id) 
    ON DELETE CASCADE ON UPDATE CASCADE 
)
