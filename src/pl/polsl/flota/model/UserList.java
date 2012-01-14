/**
 * A model class of a list of Users.
 */
package pl.polsl.flota.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pl.polsl.flota.exceptions.ElementAlredyExists;
import pl.polsl.flota.exceptions.ElementNotFound;

import flexjson.JSONDeserializer;
import flexjson.JSONException;
import flexjson.JSONSerializer;

/**
 * This class creates an handles operations on a list of Users.
 *
 * @author Marcin Jabrzyk
 * @since 1.0.2 25/10/2011
 */
public class UserList {

    /**
     * A list of user in a type of LinkedList
     */
    List<User> listOfUsers;

    /**
     * Default constructor. Only creates the list.
     *
     * @since 1.0.2 25/10/2011
     */
    public UserList() {
        super();
        this.listOfUsers = new LinkedList<User>();
    }

    /**
     * Add a user to a list of objects. If element with the same id or login
     * exists throws an ElementAlredyExists Exception.
     *
     * @param user a User object to add
     * @see User
     * @throws ElementAlredyExists
     */
    public void addUser(User user) throws ElementAlredyExists {
        for (User user_ : this.listOfUsers) {
            if ((user_.getUserId() == user.getUserId())
                    || user_.getUserName().endsWith(user.getUserName())) {
                throw new ElementAlredyExists("User: addUser "
                        + user.getUserId() + "user already exists");
            }
        }
        this.listOfUsers.add(user);

    }

    /**
     * Simply removes an User object.
     *
     * @param user a User object to remove
     */
    public void deleteItem(User user) {
        listOfUsers.remove(user);
    }

    /**
     * Returns a reference to internal list.
     *
     * @return the listOfUsers
     */
    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    /**
     * Returns a 1 user with a provided id. If don't find anything throws an
     * ElementNotFound Exception.
     *
     * @param userId a userId to find
     * @return a User object
     * @throws ElementNotFound
     */
    public User getUserById(Integer userId) throws ElementNotFound {
        for (User user : this.listOfUsers) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        throw new ElementNotFound("User: getUserById - userId" + userId
                + " not Found");
    }

    /**
     * Returns first user with the provided name. If not found throws an
     * ElementNotFound Exception.
     *
     * @param userName to find
     * @return a User object
     * @throws ElementNotFound
     */
    public User getUserByUserName(String userName) throws ElementNotFound {
        for (User user : this.listOfUsers) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        throw new ElementNotFound("User: getUserByUserName - userName"
                + userName + " not Found");
    }

    /**
     * Reads the file encoded with JSON. All read users are added to
     * listOfUsers.
     *
     * @param fileName name of file to read
     * @throws IOException
     * @since 1.0.2 25/10/2011
     */
    public void load(String fileName) throws IOException {
        try {
            FileReader fileReader = new FileReader(fileName);
            this.listOfUsers = new JSONDeserializer<List<User>>().deserialize(fileReader);
            fileReader.close();
        } catch (JSONException e) {
            System.out.println("Błąd wczytywania pliku.");
            throw new IOException();
            //e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Błędna nazwa pliku lub plik nie istnieje.");
            throw new IOException();
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Bład we/wy.");
            throw new IOException();
            //e.printStackTrace();
        }
        // We must provide a value to lastUserId after loading a file.
        User.setLastUserId(this.listOfUsers.get(this.listOfUsers.size() - 1).getUserId());
    }

    /**
     * Gets the list of Objects and save it encoded in JSON in file.
     *
     * @param fileName - name of file to save
     * @since 1.0.1 24/10/2011
     */
    // TODO: Wyłączyć println, zamiast tego throws ErrorLoadingFile - z własnych
    // wyjątków
    public void save(String fileName) {
        try {
            JSONSerializer serializer = new JSONSerializer();
            FileWriter fileWriter = new FileWriter(fileName);
            serializer.serialize(this.listOfUsers, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error przy zapisie");
        }
    }

    /**
     * @param listOfUsers the listOfUsers to set
     */
    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    /**
     * Try to update a user object with the id provided. If can't find the
     * object throws ElementNotFound exception.
     *
     * @param userId to update
     * @param user object to manipulate
     * @throws ElementNotFound
     */
    public void updateUserById(Integer userId, User user)
            throws ElementNotFound {
        Boolean isError = true;
        for (User user_ : this.listOfUsers) {
            if (user_.getUserId().compareTo(userId) == 0) {
                user_ = user;
                isError = false;
            }
        }
        if (isError) {
            throw new ElementNotFound("User: updateUserById " + userId
                    + " - element not found");
        }
    }
}
