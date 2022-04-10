#CONSTRUCTORS
INSERT IGNORE INTO constructorname (ID_CONSTRUCTOR,LABEL) values
#ROW 1
(1,"MERCEDES"),(2,"RENAULT"),(3,"DACIA"),(4,"AUDI"),(5,"BMW"),(6,"NISSAN"),
#ROW 2
(7,"CHEVROLET"),(8,"CITROEN"),(9,"FIAT"),(10,"FORD"),(11,"HONDA"),(12,"HYUNDAI"),
#ROW 3
(13,"JAGUAR"),(14,"JEEP"),(15,"KIA"),(16,"LAND-ROVER"),(17,"MASERATI"),(18,"MAZDA"),
#ROW 4
(19,"MITSUBISHI"),(20,"OPEL"),(21,"PEUGEOT"),(22,"PORSCHE"),(23,"SEAT"),(24,"SKODA"),
#ROW 5
(25,"SUZUKI"),(26,"TOYOTA"),(27,"VOLSKWAGEN"),(28,"VOLVO"),(29,"ABARTH"),(30,"DS"),
#ROW 6
(31,"LEXUS"),(32,"LAMBORGHINI"),(33,"MINI"),(34,"SMART"),(35,"TESLA"),(36,"FERRARI");

#MODELS
INSERT IGNORE INTO model (ID_MODEL,LABEL,ID_CONSTRUCTOR) values
#ROW 1
#MERCEDES
(1,"C-Class",1),(2,"E-Class",1),(3,"S-Class",1),(4,"B-Class",1),(5,"A-Class",1),(6,"GLC",1),(7,"GLE",1),(8,"GLS",1),(9,"GLA",1),
(10,"GLB",1),(11,"G-Class",1),(12,"CLA Coupé",1),(13,"C-Class Coupé",1),(14,"E-Class Coupé",1),(15,"CLS",1),(16,"AMG GT 4-Door Coupé",1),
(17,"AMG GT",1),(18,"C-Class Cabriolet",1),(19,"E-Class Cabriolet",1),(20,"GLC Coupé",1),(21,"GLE Coupé",1),(22,"SLC",1),
#RENAULT
(23,"Captur",2),(24,"Clio V",2),(25,"Clio IV",2),(26,"Express",2),(27,"Express Van",2),(28,"Kadjar",2),
(29,"Kangoo",2),(30,"Koleos",2),(31,"Master",2),(32,"Megane",2),(33,"Talisman",2),(34,"Trafic",2),
#DACIA
(35,"Dokker",3),(36,"Dokker Van",3),(37,"Duster",3),(38,"Lodgy",3),(39,"Logan",3),(40,"Sandero",3),
#AUDI
(41,"A1",4),(42,"A2",4),(43,"A3",4),(44,"A4",4),(45,"A5",4),(46,"A6",4),(47,"A7",4),(48,"A8",4),
(49,"A5 Sportback",4),(50,"E-Tron",4),(51,"E-Tron Sportback",4),(52,"Q2",4),(53,"Q3",4),(54,"Q3 Sportback",4),
(55,"Q5",4),(56,"Q5 Sportback",4),(57,"Q7",4),(58,"Q8",4),(59,"R8",4),(60,"TT",4),
#BMW
(61,"Serie 1",5),(62,"Serie 2",5),(63,"Serie 3",5),(64,"Serie 4",5),(65,"Serie 5",5),(66,"Serie 6",5),(67,"Serie 7",5),
(68,"Serie 8",5),(69,"X1",5),(70,"X2",5),(71,"X3",5),(72,"X4",5),(73,"X5",5),(74,"X6",5),(75,"X7",5),(76,"Z4",5),
(77,"Serie 2 Gran Coupé",5),(78,"Serie 3 GT",5),(79,"Serie 4 Gran Coupé",5),(80,"Serie 4 Cabriolet",5),(81,"Serie 8 Gran Coupé",5),
#NISSAN
(82,"Evalia",6),(83,"Juke",6),(84,"Micra",6),(85,"Navara",6),(86,"Qashqai",6),(87,"X Trail",6),
#ROW 2
#CHEVROLET
(88,"Captiva",7),(89,"Cruze",7),(90,"Spark",7),(91,"Orlando",7),
#CITROEN
(92,"Ami",8),(93,"Berlingo",8),(94,"C-elysee",8),(95,"C1",8),(96,"C3",8),(97,"C4",8),
(98,"Jumper",8),(99,"Jumpy",8),(100,"Nemo",8),(101,"SpaceTourer",8),
#FIAT
(102,"500",9),(103,"500c",9),(104,"500x",9),(105,"Doblo",9),(106,"Fiorino",9),(107,"Fullback",9),
(108,"Panda",9),(109,"Punto",9),(110,"Talento",9),(111,"Tipo",9),
#FORD
(112,"C Max",10),(113,"Ecosport",10),(114,"Everest",10),(115,"Explorer",10),(116,"Fiesta",10),
(117,"Focus",10),(118,"Fusion",10),(119,"Kuga",10),(120,"Mustang",10),(121,"Ranger",10),(122,"Transit",10),
#HONDA
(123,"Accord",11),(124,"Civic",11),(125,"Jazz",11),(126,"CR-V",11),(127,"HR-V",11),
#HYUNDAI
(128,"Accent",12),(129,"Creta",12),(130,"Elantra",12),(131,"I10",12),(132,"I20",12),(133,"I30",12),
(134,"Ionic",12),(135,"Kona",12),(136,"Santa Fe",12),(137,"Sonata",12),(138,"Tucson",12),
#ROW 3
#JAGUAR
(139,"E-PACE",13),(140,"F-PACE",13),(141,"F-TYPE",13),(142,"XE",13),(143,"I-PACE",13),(144,"XF",13),(145,"XJ",13),
#JEEP
(146,"Cherokee",14),(147,"Compass",14),(148,"Renegade",14),(149,"Wrangler",14),(150,"Grand Cherokee",14),
#KIA
(151,"Carnival",15),(152,"Ceed",15),(153,"Cerato",15),(154,"K2500",15),(155,"K5",15),(156,"Niro",15),
(157,"Picanto",15),(158,"Rio",15),(159,"Seltos",15),(160,"Sorento",15),(161,"Sportage",15),(162,"Stinger",15),
#LAND-ROVER
(163,"Defender",16),(164,"Discovery",16),(165,"Range Rover Evoque",16),(166,"Range Rover Sport",16),
(167,"Range Rover Vogue",16),(168,"Range Rover Velar",16),(169,"Discovery Sport",16),
#MASERATI
(170,"Ghibli",17),(171,"Grancabrio",17),(172,"Granturismo",17),(173,"Levante",17),(174,"Quattroporte",17),
#MAZDA
(175,"6",18),(176,"3",18),(178,"Cx-3",18),(179,"Cx-5",18),(180,"2",18),(181,"Cx-30",18),(182,"Cx-8",18),
(183,"Cx-9",18),(184,"Mx-5",18),(185,"Mx-30",18),(186,"Bt-40",18),
#ROW 4
#MITSUBISHI
(187,"L200",19),(188,"Outlander",19),(189,"Pajero Sport",19),
#OPEL
(190,"Adam",20),(191,"Astra",20),(192,"Combo Life",20),(193,"Corsa",20),(194,"Combo Cargo",20),(195,"Crossland",20),
(196,"Grandland X",20),(197,"Insignia",20),(198,"Mokka",20),(199,"Movano",20),(200,"Vivaro",20),
#PEUGEOT
(201,"108",21),(202,"208",21),(203,"2008",21),(204,"3008",21),(205,"301",21),(206,"308",21),(207,"5008",21),
(208,"508",21),(209,"Bipper",21),(210,"Boxer",21),(211,"Expert",21),(212,"Landtrek",21),(213,"Partner",21),
(214,"Rifter",21),(215,"Traveller",21),
#PORSCHE
(216,"718 Boxter",22),(217,"718 Cayman",22),(218,"911",22),(311,"911 Targa",22),(219,"Cayenne",22),(220,"Cayenne Coupé",22),
(221,"Macan",22),(222,"Panamera",22),(223,"Taycan",22),
#SEAT
(224,"Arona",23),(225,"Ateca",23),(226,"Leon",23),(227,"Ibiza",23),(228,"Mii",23),
#SKODA
(229,"Fabia",24),(230,"Kamiq",24),(231,"Karoq",24),(232,"Kodiaq",24),(233,"Octavia",24),
(234,"Scala",24),(235,"Superb",24),
#ROW 5
#SUZUKI
(236,"Jimny",25),(237,"Swift",25),(238,"Vitara",25),(239,"Ignis",25),(240,"Baleno",25),(241,"S-Cross",0),
#TOYOTA
(242,"Auris",26),(243,"CH-R",26),(244,"Corolla Cross",26),(245,"Corolla Prestige",26),(246,"Corolla Sport",26),
(247,"Fortuner",26),(248,"Hilux",26),(249,"Land Cruiser",26),(250,"Rav4",26),(251,"Prado",26),(252,"Prius",26),
(253,"Yaris",26),(254,"Yaris Cross",26),
#VOLSKWAGEN
(255,"Amarok",27),(256,"Arteon",27),(257,"Caddy",27),(258,"Caddy Maxi",27),(259,"Caddy Van",27),(260,"Caravelle",27),
(261,"Coccinelle",27),(262,"Crafter",27),(263,"Golf 8",27),(264,"Golf 7",27),(265,"Jetta",27),(266,"Passat",27),
(267,"Polo",27),(268,"T-Roc",27),(269,"Tiguan",27),(270,"Touareg",27),(271,"Touran",27),
#VOLVO
(272,"S60",28),(273,"S90",28),(274,"V40",28),(275,"V90",28),(276,"XC40",28),(277,"XC60",28),(278,"XC90",28),
#ABARTH
(279,"595",29),(280,"595 Cabriolet",29),
#DS
(281,"DS3 Crossback",30),(282,"DS7 Crossback",30),
#ROW 6
#LEXUS
(283,"ES",31),(284,"IS",31),(285,"LS",31),(286,"LX",31),(287,"NX",31),(288,"RX",31),(289,"UX",31),
#LAMBORGHINI
(290,"Aventador",32),(291,"Urus",32),(292,"Huracan",32),
#MINI
(293,"Cabrio",33),(294,"Clubman",33),(295,"Countryman",33),(296,"Hatch 3 portes",33),(297,"Hatch 5 portes",33),
#SMART
(298,"Fortwo",34),(299,"ForFour",34),(300,"Crossblade",34),(301,"Roadster",34),
#TESLA
(302,"Model S",35),(303,"Model X",35),(304,"Model 3",35),
#FERRARI
(305,"GTC4 Lusso",36),(306,"812 SuperFast",36),(307,"488",36),(308,"Roma",36),(309,"458",36),(310,"599 GTO",36);

#TRIMS
INSERT IGNORE INTO trim (ID_TRIM,LABEL,ID_CONSTRUCTOR) values
#ROW 1
#MERCEDES
(1,"180d",1),(2,"180d Style",1),(3,"180d Progressive",1),(4,"180d Distinctive",1),(5,"180d AMG Line",1),(6,"180d AMG Line+",1),(7,"200",1),
(8,"200 Style",1),(9,"200 Progressive",1),(10,"200 Distinctive",1),(11,"200 AMG Line",1),(12,"200 AMG Line+",1),(13,"220d",1),(14,"220d Style",1),
(15,"220d Progressive",1),(16,"220d Distinctive",1),(17,"220d AMG Line",1),(18,"220d AMG Line+",1),(19,"250",1),(20,"250 Style",1),(21,"250 Progressive",1),
(22,"250 Distinctive",1),(23,"250 AMG Line",1),(24,"250 AMG Line+",1),(25,"300",1),(26,"300 Business",1),(27,"300 Luxury",1),(28,"300 Avantgarde",1),
(29,"300 AMG Line",1),(30,"300 AMG Line+",1),(31,"350",1),(32,"350 Business",1),(33,"350 Luxury",1),(34,"350 Avantgarde",1),(35,"350 AMG Line",1),
(36,"350 AMG Line+",1),(37,"400 4 MATIC",1),(38,"400 Business 4 MATIC",1),(39,"400 Luxury 4 MATIC",1),(40,"400 Avantgarde 4 MATIC",1),(41,"400 AMG Line 4 MATIC",1),
(42,"400 AMG Line+ 4 MATIC",1),(43,"450 4 MATIC",1),(44,"450 Business 4 MATIC",1),(45,"450 Luxury 4 MATIC",1),(46,"450 Avantgarde 4 MATIC",1),(47,"450 AMG Line 4 MATIC",1),
(48,"450 AMG Line+ 4 MATIC",1),(49,"500 4 MATIC",1),(50,"500 Business 4 MATIC",1),(51,"500 Luxury 4 MATIC",1),(52,"500 AMG Line 4 MATIC",1),(53,"500 AMG Line+ 4 MATIC",1),
(54,"35 AMG",1),(55,"43 AMG",1),(56,"45 AMG",1),(57,"55 AMG",1),(58,"53 AMG",1),(59,"63 AMG",1),
#RENAULT
(60,"Life",2),(61,"Explore",2),(62,"Intens",2),(63,"RS Line",2),
#DACIA
(64,"Confort",3),(65,"Essentiel",3),(66,"Prestige",3),(67,"Confort",3),
#AUDI
(68,"30 TDI",4),(69,"30 TDI Design",4),(70,"30 TDI Premium",4),(71,"30 TDI Sport",4),(72,"30 TDI SLine",4),(73,"35 TDI",4),(74,"35 TDI Design",4),(75,"35 TDI Premium",4),
(76,"35 TDI Sport",4),(77,"35 TDI SLine",4),(78,"40 TDI Premium",4),(79,"40 TDI Business",4),(80,"40 TDI SLine",4),(81,"45 TDI Quattro Ambassadeur",4),(82,"45 TDI Quattro Business",4),
(83,"45 TDI Quattro SLine",4),(84,"45 TDI Quattro Prestige",4),(85,"45 TDI Quattro Prestige",4),(86,"50 TDI Quattro SLine",4),(87,"50 TDI Quattro Sport S",4),(88,"RS",4),(89,"TFSI",4),
#BMW
(90,"16d Lounge",5),(91,"16d Sport",5),(92,"16d Pack M",5),(93,"18d Lounge",5),(94,"18d Sport",5),(95,"18d Pack M",5),(96,"18i Lounge",5),(97,"18i Sport",5),(98,"18i Pack M",5),
(99,"20d Lounge",5),(100,"20d Sport",5),(101,"20d Pack M",5),(102,"20i Avantage",5),(103,"20i Modern",5),(104,"20i Luxury",5),(105,"28i Avantage",5),(106,"28i Sport",5),(107,"28i Modern",5),
(108,"28i Luxury",5),(109,"30i Luxury",5),(110,"30i Sport",5),(111,"30i Pack M",5),(112,"30d Luxury",5),(113,"30d Sport",5),(114,"30d Pack M",5),(115,"30d Avantage",5),(116,"40d Confort Line",5),
(117,"40d Exclusive Line",5),(118,"40i Confort Line",5),(119,"40i Exclusive Line",5),(120,"35i xDrive Confort",5),(121,"35i xDrive Exclusive",5),(122,"30e Avantage",5),(123,"30e Sport Line",5),
(124,"30e Exclusive Line",5),(125,"30e Pack M",5),(126,"M50",5),(127,"M",5),(128,"50i Confort Line",5),(129,"50i Exclusive Line",5),
#NISSAN
(130,"Visia",6),(131,"Acenta",6),(132,"Tekna",6),(133,"Connecta",6),
#ROW 2
#CHEVROLET
(134,"LS",7),(135,"LT",7),(136,"LTZ",7),
#CITROEN
(137,"Feel",8),(138,"Shine",8),
#FIAT
(139,"POP",9),(140,"EASY",9),(141,"LOUNGE",9),(142,"CONNECT",9),
#FORD
(143,"Connected",10),(144,"Connected plus",10),(145,"Active",10),(146,"ST Line",10),(147,"Trend",10),(148,"Trend plus",10),(149,"Titanium",10),(150,"Titanium X",10),
#HONDA
(151,"Confort I-DTEC",11),(152,"Elegance I-DTEC",11),(153,"LXIA I-VTEC",11),(154,"VTIA I-VTEC",11),(155,"Trend I-VTEC",11),
#HYUNDAI
(156,"Attractive",12),(157,"Inventive",12),(158,"Seductive",12),(159,"Premium",12),(160,"Prestige",12),(161,"Luxe",12),(162,"Ultimate",12),
#ROW 4
#JAGUAR
(163,"2.0L Diesel Standard",13),(164,"2.0L Diesel S",13),(165,"2.0L Diesel Std SE",13),(166,"2.0L Diesel Std HSE",13),(167,"2.0L Diesel Dynamic S",13),
(168,"2.0L Essence Standard",13),(169,"2.0L Essence S",13),(170,"5.0L Essence SVR",13),(171,"3.0L Essence Luxe",13),(172,"3.0L Essence Portfolio",13),
(173,"3.0L Diesel Premium",13),
#JEEP
(174,"Longitude",14),(175,"Limited",14),(176,"Sahara",14),(177,"Sport",14),(178,"Rubicon",14),(179,"Night Eagle",14),
#KIA
(180,"Active",15),(181,"Active+",15),(182,"GT Line",15),(183,"Executive",15),(184,"Executive+",15),(185,"Motion",15),(186,"Motion+",15),(187,"Design",15),(188,"Design+",15),
#LAND-ROVER
(189,"110 S",16),(190,"110 SE",16),(191,"3.0L Diesel S",16),(192,"3.0L Diesel SE",16),(193,"3.0L Essence S",16),(194,"3.0L Essence SE",16),(195,"SD4 HSE",16),
(196,"Standard",16),(197,"5.0L Essence SVR",16),(198,"2.0L Diesel S",16),(199,"2.0L Diesel SE",16),(200,"Autobiography",16),
#MASERATI
(201,"3.0 V6",17),(202,"3.0 V6 D",17),(203,"3.0 V6 S",17),(204,"3.0 V6 S Q4",17),(205,"4.7 V8",17),(206,"4.7 V8 Sport",17),
(207,"4.7 V8 MC",17),(208,"4.7 V8 MC Stradale",17),(209,"3.8 V8 GTS",17),
#MAZDA
(210,"Elegance",18),(211,"Selection",18),(212,"Harmony",18),
#ROW 4
#MITSUBISHI
(213,"Intense",19),(214,"Instyle",19),(215,"Premium",19),
#OPEL
(216,"Edition Diesel",20),(217,"Edition Essence",20),(218,"GS Line Diesel",20),(219,"GS Line + Diesel",20),(220,"Elegance",20),
#PEUGEOT
(221,"Active",21),(222,"Allure",21),(223,"Gt Line",21),(224,"Access",21),
#PORSCHE
(225,"Default",22),(226,"S",22),(227,"Coupé",22),(228,"Cabriolet",22),(229,"Turbo S",22),(230,"GTS",22),(231,"4S",22),
#SEAT
(232,"TDI Move",23),(233,"TDI Style",23),(234,"TDI Style+",23),(235,"TDI Urabn+",23),(236,"TDI Xperience",23),(237,"TDI FR",23),(238,"TDI FR+",23),
(239,"MPI Reference+",23),(240,"MPI Style+",23),(241,"MPI Black&White+",23),(242,"MPI Urban+",23),
#SKODA
(243,"Elegance",24),(244,"Style",24),(245,"Business",24),(246,"Elegance",24),(247,"Active",24),(248,"Ambition",24),
#ROW 5
#SUZUKI
(249,"1.5L VTT",25),(250,"1.6L Avantage",25),(251,"1.6L Privilege",25),
#TOYOTA
(252,"Dynamic",26),(253,"Dynamic+",26),(254,"Distinctive",26),(255,"Distinctive+",26),
#VOLSKWAGEN
(256,"Life",27),(257,"Style",27),(258,"Carat",27),(259,"Elegance",27),(260,"Confort",27),(261,"Atmosphere",27),(262,"R-Line",27),
#VOLVO
(1,"",28),
#ABARTH
#DS
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
(1,"",1),
();

