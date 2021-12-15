import java.security.MessageDigest;

public class User {
    private int id;
    private int IDcounter;
    private String email;
    private String password;
    private int[] projects;
    private String name;
    private String bio;
    private String photo;
    private String location;
    
    public User(int id){
        this.id = id;
    }

    public User(int id, String email, String password, String name, String bio, String photo, String location){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.photo = photo;
        this.location = location;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getMd5Password(){
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = this.password.getBytes("UTF-8");
        byte[] result = md.digest(passwordBytes);
        BigInteger bigInt = new BigInteger(1,result);
        String hash = bigInt.toString(16);
        return hash;
        }

    //is n the index of the element that should be removed?
    public void addProject(int n){
        int[] copy = new int[this.projects.length + 1];
        for(int i = 0; i < this.projects.length - 1; i++){
            if(i != n){
                copy[i] = this.projects[i];
            }

        }
        this.projects = copy;
    }

    public void removeProject(int r){
        for(int i = r; i < this.projects.length; i++)
        {
            this.projects[i] = this.projects[i + 1];
        }
    }

    public int[] getProjects(){
        return this.projects;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getBio(){
        return this.bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public String getPhoto(){
        return this.photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }


    // public int createID(){
    //     int ID;
    //     int digit;
    //     String StringID = "";
    //     for(int i = 0; i < 5; i++){
    //         digit = (int)(Math.random() * 10);
    //         StringID += digit;
    //     }
    //     ID = Integer.valueOf(StringID);
    //     return ID;
    // }
}
