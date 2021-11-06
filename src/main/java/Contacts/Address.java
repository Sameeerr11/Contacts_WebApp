package Contacts;

    public class Address {

        private int address_id;
        private String address_type = " ";
        private String street_address;
        private String city;
        private String state;
        private String zip;
        private int contact_id;

        public Address(){
        }

        public Address(String address_type, String street_address, String city, String state, String zip, int address_id){
            this.address_type = address_type;
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.address_id =address_id;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress_type() {
            return address_type;
        }

        public void setAddress_type(String address_type) {
            this.address_type = address_type;
        }

        public String getStreet_address() {
            return street_address;
        }

        public void setStreet_address(String street_address) {
            this.street_address = street_address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public int getContact_id() {
            return contact_id;
        }

        public void setContact_id(int contact_id) {
            this.contact_id = contact_id;
        }


}
