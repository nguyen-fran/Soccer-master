package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author Francisco Nguyen
 * @version 9/24/2020
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> playersDB = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {

        //check if there's already a soccer player with the name from the param in players hashtable
        if (playersDB.containsKey(firstName + lastName)) {
            this.removePlayer(firstName, lastName);
        }
        SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        playersDB.put(firstName + lastName, player);

        return true;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.remove(playerKey);
            return  true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            return playersDB.get(playerKey);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String playerKey = firstName + lastName;
        if (playersDB.containsKey(playerKey)) {
            playersDB.get(playerKey).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int playersInTeamCount = 0;
        if (teamName == null) {
            return playersDB.size();
        }
        for (SoccerPlayer player : playersDB.values()) {
            if (player.getTeamName().equals(teamName)) {
                playersInTeamCount++;
            }
        }
        return playersInTeamCount;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        if (idx > this.numPlayers(teamName)) {
            return null;
        }

        int count = 0;
        for (SoccerPlayer player : playersDB.values()) {
            if (player.getTeamName().equals(teamName)) {
                if (count == idx) {
                    return player;
                }
                count++;
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        try {
            String firstName, lastName, playerKey, teamName;
            int uniform, goals, assists, shots, fouls, saves, redCards, yellowCards;
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                firstName = scan.nextLine();
                lastName = scan.nextLine();
                uniform = Integer.parseInt(scan.nextLine());
                teamName = scan.nextLine();
                playerKey = firstName + lastName;
                goals = Integer.parseInt(scan.nextLine());
                assists = Integer.parseInt(scan.nextLine());
                shots = Integer.parseInt(scan.nextLine());
                fouls = Integer.parseInt(scan.nextLine());
                saves = Integer.parseInt(scan.nextLine());
                redCards = Integer.parseInt(scan.nextLine());
                yellowCards = Integer.parseInt(scan.nextLine());

                this.addPlayer(firstName, lastName, uniform, teamName);
                while (playersDB.get(playerKey).getGoals() < goals) playersDB.get(playerKey).bumpGoals();
                while (playersDB.get(playerKey).getAssists() < assists) playersDB.get(playerKey).bumpAssists();
                while (playersDB.get(playerKey).getShots() < shots) playersDB.get(playerKey).bumpShots();
                while (playersDB.get(playerKey).getFouls() < fouls) playersDB.get(playerKey).bumpFouls();
                while (playersDB.get(playerKey).getSaves() < saves) playersDB.get(playerKey).bumpSaves();
                while (playersDB.get(playerKey).getRedCards() < redCards) playersDB.get(playerKey).bumpRedCards();
                while (playersDB.get(playerKey).getYellowCards() < yellowCards) playersDB.get(playerKey).bumpYellowCards();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (SoccerPlayer player : playersDB.values()) {
                writer.println(logString(player.getFirstName()));
                writer.println(logString(player.getLastName()));
                writer.println(logString(Integer.toString(player.getUniform())));
                writer.println(logString(player.getTeamName()));
                writer.println(logString(Integer.toString(player.getGoals())));
                writer.println(logString(Integer.toString(player.getAssists())));
                writer.println(logString(Integer.toString(player.getShots())));
                writer.println(logString(Integer.toString(player.getFouls())));
                writer.println(logString(Integer.toString(player.getSaves())));
                writer.println(logString(Integer.toString(player.getRedCards())));
                writer.println(logString(Integer.toString(player.getYellowCards())));
            }
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        //Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
