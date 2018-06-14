package com.us.example.util;

public class EnumConstant {
    public static enum VisaType {
        Ordinary, Courtesy, Diplomatic
    }
    public static enum AccountStatus{
        Active,Locked,Revoked
    }
    public static enum ApplicationStatus{
        Pending(1),Submitted(2),Approved(3),Denied(4);
        private int statusValue;

        public int getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(int statusValue) {
            this.statusValue = statusValue;
        }

        ApplicationStatus(int statusValue) {
            this.statusValue = statusValue;
        }
    }
    public static enum Citizenship{
        Afghanistan("Afghanistan"),Albania("Albania"),Algeria("Algeria"),Andorra("Andorra"),Angola("Angola"),Antigua("Antigua and Barbuda"),
        Argentina("Argentina"),Armenia("Armenia"),Aruba("Aruba"),Australia("Australia"),Austria("Austria"),Azerbaijan("Azerbaijan"),Bahamas("Bahamas, The"),Bahrain("Bahrain"),Bangladesh("Bangladesh"),
        Barbados("Barbados"),Belarus("Belarus"),Belgium("Belgium"),Belize("Belize"),Benin("Benin"),Bhutan("Bhutan"),Bolivia("Bolivia"),Bosnia("Bosnia and Herzegovina"),Botswana("Botswana"),Brazil("Brazil"),Brunei("Brunei"),Bulgaria("Bulgaria"),Burkina("Burkina Faso"),Burma("Burma"),Burundi("Burundi"),
        Cambodia("Cambodia"),Cameroon("Cameroon"),Canada("Canada"),CaboVerde("Cabo Verde"),CentralAfricanRepublic("Central African Republic"),Chad("Chad"),Chile("Chile"),China("China"),Colombia("Colombia"),Comoros("Comoros"),/*CongoDemocraticRepublicofthe("Congo, Democratic Republic of the"),*/CongoRepublicofthe("Congo, Republic of the"),CostaRica("Costa Rica"),CotedIvoire("Cote d'Ivoire"),Croatia("Croatia"),Cuba("Cuba"),Curacao("Curacao"),Cyprus("Cyprus"),Czechia("Czechia"),
        Denmark("Denmark"),Djibouti("Djibouti"),Dominica("Dominica"),DominicanRepublic("Dominican Republic"),
        /*EastTimor("East Timor (see Timor-Leste)"),*/Ecuador("Ecuador"),Egypt("Egypt"),ElSalvador("El Salvador"),EquatorialGuinea("Equatorial Guinea"),Eritrea("Eritrea"),Estonia("Estonia"),Ethiopia("Ethiopia"),
        Fiji("Fiji"),Finland("Finland"),France("France"),
        Gabon("Gabon"),GambiaThe("Gambia, The"),Georgia("Georgia"),Germany("Germany"),Ghana("Ghana"),Greece("Greece"),Grenada("Grenada"),Guatemala("Guatemala"),Guinea("Guinea"),GuineaBissau("Guinea-Bissau"),Guyana("Guyana"),
        Haiti("Haiti"),HolySee("Holy See"),Honduras("Honduras"),/*HongKong("Hong Kong"),*/Hungary("Hungary"),
        Iceland("Iceland"),India("India"),Indonesia("Indonesia"),Iran("Iran"),Iraq("Iraq"),Ireland("Ireland"),Israel("Israel"),Italy("Italy"),
        Jamaica("Jamaica"),Japan("Japan"), Jordan("Jordan"),
        Kazakhstan("Kazakhstan"), Kenya("Kenya"),Kiribati("Kiribati"), KoreaNorth("Korea, North"),KoreaSouth("Korea, South"), Kosovo("Kosovo"), Kuwait("Kuwait"), Kyrgyzstan("Kyrgyzstan"),
        Laos("Laos"),Latvia("Latvia"), Lebanon("Lebanon"), Lesotho("Lesotho"), Liberia("Liberia"), Libya("Libya"), Liechtenstein("Liechtenstein"), Lithuania("Lithuania"),Luxembourg("Luxembourg"),
        /*Macau("Macau"), */Macedonia("Macedonia"), Madagascar("Madagascar"), Malawi("Malawi"), Malaysia("Malaysia"), Maldives("Maldives"), Mali("Mali"), Malta("Malta"), MarshallIslands("Marshall Islands"), Mauritania("Mauritania"), Mauritius("Mauritius"), Mexico("Mexico"), Micronesia("Micronesia"), Moldova("Moldova"), Monaco("Monaco"), Mongolia("Mongolia"), Montenegro("Montenegro"), Morocco("Morocco"), Mozambique("Mozambique"),
        Namibia("Namibia"), Nauru("Nauru"), Nepal("Nepal"), Netherlands("Netherlands"), NewZealand("New Zealand"),Nicaragua("Nicaragua"), Niger("Niger"), Nigeria("Nigeria"), Norway("Norway"),
        Oman("Oman"),
        Pakistan("Pakistan"), Palau("Palau"), PalestinianTerritories("Palestinian Territories"), Panama("Panama"), PapuaNewGuinea("Papua New Guinea"), Paraguay("Paraguay"),Peru("Peru"), Philippines("Philippines"), Poland("Poland"), Portugal("Portugal"),
        Qatar("Qatar"),
        Romania("Romania"), Russia("Russia"),Rwanda("Rwanda"),
        SaintKittsandNevis("Saint Kitts and Nevis"), SaintLucia("Saint Lucia"), /*SaintVincentandtheGrenadines("Saint Vincent and the Grenadines"),*/ Samoa("Samoa"), SanMarino("San Marino"), SaoTomeandPrincipe("Sao Tome and Principe"), SaudiArabia("Saudi Arabia"), Senegal("Senegal"), Serbia("Serbia"), Seychelles("Seychelles"), SierraLeone("Sierra Leone"), Singapore("Singapore"), SintMaarten("Sint Maarten"), Slovakia("Slovakia"), Slovenia("Slovenia"), SolomonIslands("Solomon Islands"), Somalia("Somalia"), SouthAfrica("South Africa"), SouthSudan("South Sudan"), Spain("Spain"), SriLanka("Sri Lanka"), Sudan("Sudan"), Suriname("Suriname"), Swaziland("Swaziland"), Sweden("Sweden"), Switzerland("Switzerland"), Syria("Syria"),
        /*Taiwan("Taiwan"), */Tajikistan("Tajikistan"), Tanzania("Tanzania"), Thailand("Thailand"),TimorLeste("Timor-Leste"),Togo("Togo"), Tonga("Tonga"), TrinidadandTobago("Trinidad and Tobago"), Tunisia("Tunisia"), Turkey("Turkey"), Turkmenistan("Turkmenistan"), Tuvalu("Tuvalu"),
        Uganda("Uganda"), Ukraine("Ukraine"), UnitedArabEmirates("United Arab Emirates"), UnitedKingdom("United Kingdom"),Uruguay("Uruguay"),Uzbekistan("Uzbekistan"),
        Vanuatu("Vanuatu"), Venezuela("Venezuela"),Vietnam("Vietnam"),
        Yemen("Yemen"),
        Zambia("Zambia"), Zimbabwe("Zimbabwe");

        private String countryname;
        Citizenship(String countryname) {
            this.countryname=countryname;
        }

        public String getCountryname() {
            return countryname;
        }

        public void setCountryname(String countryname) {
            this.countryname = countryname;
        }
    }
    public static enum MaritalStatus{
        Single(1),Married(2);
        private int statusValue;

        public int getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(int statusValue) {
            this.statusValue = statusValue;
        }

        MaritalStatus(int statusValue) {
            this.statusValue = statusValue;
        }
    }
    public static enum Gender{
        Male, Female
    }
    public static enum NearestEmbassy{
        Washington
    }

}
