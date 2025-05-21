package forensic;

/**
 * This class represents a forensic analysis system that manages DNA data using
 * BSTs.
 * Contains methods to create, read, update, delete, and flag profiles.
 * 
 * @author Kal Pandit
 */
public class ForensicAnalysis {

    private TreeNode treeRoot;            // BST's root
    private String firstUnknownSequence;
    private String secondUnknownSequence;

    public ForensicAnalysis () {
        treeRoot = null;
        firstUnknownSequence = null;
        secondUnknownSequence = null;
    }

    /**
     * Builds a simplified forensic analysis database as a BST and populates unknown sequences.
     * The input file is formatted as follows:
     * 1. one line containing the number of people in the database, say p
     * 2. one line containing first unknown sequence
     * 3. one line containing second unknown sequence
     * 2. for each person (p), this method:
     * - reads the person's name
     * - calls buildSingleProfile to return a single profile.
     * - calls insertPerson on the profile built to insert into BST.
     *      Use the BST insertion algorithm from class to insert.
     * 
     * DO NOT EDIT this method, IMPLEMENT buildSingleProfile and insertPerson.
     * 
     * @param filename the name of the file to read from
     */
    public void buildTree(String filename) {
        // DO NOT EDIT THIS CODE
        StdIn.setFile(filename); // DO NOT remove this line

        // Reads unknown sequences
        String sequence1 = StdIn.readLine();
        firstUnknownSequence = sequence1;
        String sequence2 = StdIn.readLine();
        secondUnknownSequence = sequence2;
        
        int numberOfPeople = Integer.parseInt(StdIn.readLine()); 

        for (int i = 0; i < numberOfPeople; i++) {
            // Reads name, count of STRs
            String fname = StdIn.readString();
            String lname = StdIn.readString();
            String fullName = lname + ", " + fname;
            // Calls buildSingleProfile to create
            Profile profileToAdd = createSingleProfile();
            // Calls insertPerson on that profile: inserts a key-value pair (name, profile)
            insertPerson(fullName, profileToAdd);
        }
    }

    /** 
     * Reads ONE profile from input file and returns a new Profile.
     * Do not add a StdIn.setFile statement, that is done for you in buildTree.
    */
    public Profile createSingleProfile() 
    {   
        int size = StdIn.readInt();
        STR[] strs = new STR[size];
        for(int i=0; i<size; i++)
        {
            String strString = StdIn.readString();
            int occurrences = StdIn.readInt();
            STR newSTR = new STR(strString, occurrences);
            strs[i] = newSTR;
        }
        Profile newProfile = new Profile(strs);
        return newProfile; // update this line
    }

    /**
     * Inserts a node with a new (key, value) pair into
     * the binary search tree rooted at treeRoot.
     * 
     * Names are the keys, Profiles are the values.
     * USE the compareTo method on keys.
     * 
     * @param newProfile the profile to be inserted
     */
    public void insertPerson(String name, Profile newProfile) 
    {
        TreeNode insertedNode = new TreeNode(name, newProfile, null, null);
        TreeNode rootPtr = treeRoot;
        TreeNode rootPrevious = null;
        if(treeRoot == null)
        {
            treeRoot = insertedNode;
        }
        else
        {
            int difference=0;
            while(rootPtr!=null)
            {
                rootPrevious = rootPtr;
                difference  = rootPtr.getName().compareTo(name);
                if(difference>0)
                {
                rootPtr = rootPtr.getLeft();
                }
                else
                {
                rootPtr = rootPtr.getRight();
                }
                
            }
            if(difference>0)
            {
            rootPrevious.setLeft(insertedNode);
            }
            else
            {
            rootPrevious.setRight(insertedNode);
            }
        }
    }

    /**
     * Finds the number of profiles in the BST whose interest status matches
     * isOfInterest.
     *
     * @param isOfInterest the search mode: whether we are searching for unmarked or
     *                     marked profiles. true if yes, false otherwise
     * @return the number of profiles according to the search mode marked
     */
    public int getMatchingProfileCount(boolean isOfInterest) 
    {
        int numOfMatchingProfiles = orderedAndChecked(treeRoot, isOfInterest);
        return numOfMatchingProfiles;
    }

    private int orderedAndChecked(TreeNode x, boolean isOfInterest)
    {
        int count = 0;
        if(x==null)
        {
            return 0;
        }
        Profile currProf = x.getProfile();
        if(currProf.getMarkedStatus() == isOfInterest)
        {
            count = 1;
        }
        count+=orderedAndChecked(x.getLeft(), isOfInterest);
        count+=orderedAndChecked(x.getRight(), isOfInterest);
        return count;
    }

    /**
     * Helper method that counts the # of STR occurrences in a sequence.
     * Provided method - DO NOT UPDATE.
     * 
     * @param sequence the sequence to search
     * @param STR      the STR to count occurrences of
     * @return the number of times STR appears in sequence
     */
    private int numberOfOccurrences(String sequence, String STR) {
        
        // DO NOT EDIT THIS CODE
        
        int repeats = 0;
        // STRs can't be greater than a sequence
        if (STR.length() > sequence.length())
            return 0;
        
            // indexOf returns the first index of STR in sequence, -1 if not found
        int lastOccurrence = sequence.indexOf(STR);
        
        while (lastOccurrence != -1) {
            repeats++;
            // Move start index beyond the last found occurrence
            lastOccurrence = sequence.indexOf(STR, lastOccurrence + STR.length());
        }
        return repeats;
    }

    /**
     * Traverses the BST at treeRoot to mark profiles if:
     * - For each STR in profile STRs: at least half of STR occurrences match (round
     * UP)
     * - If occurrences THROUGHOUT DNA (first + second sequence combined) matches
     * occurrences, add a match
     */
    public void flagProfilesOfInterest() 
    {

        orderedAndFlagged(treeRoot);
    }

    private void orderedAndFlagged(TreeNode x)
    {
        if (x==null)
        {
            return;
        }
        orderedAndFlagged(x.getLeft());

        //checking if the profile is marked or not
        Profile currProf = x.getProfile();
        if(isOfInterest(currProf))
        {
            currProf.setInterestStatus(true);
        }
        else
        {
            currProf.setInterestStatus(false);
        }
        orderedAndFlagged(x.getRight());
    }

    private boolean isOfInterest(Profile profile)
    {
        STR[] strsForProfile = profile.getStrs();
        int numOfStrsForProfile = strsForProfile.length;
        int halfOfStrsForProfile = 0;
        if(numOfStrsForProfile % 2 != 0)
        {
            halfOfStrsForProfile = (numOfStrsForProfile+1)/2;
        }
        else
        {
            halfOfStrsForProfile = numOfStrsForProfile/2;
        }

        int numOfMatchingStrs = 0;
        for(int i=0;i<numOfStrsForProfile;i++)
        {
            int numOfSequence1Occurances = numberOfOccurrences(this.firstUnknownSequence, strsForProfile[i].getStrString());
            int numOfSequence2Occurances = numberOfOccurrences(this.secondUnknownSequence, strsForProfile[i].getStrString());
            if(numOfSequence1Occurances+numOfSequence2Occurances == strsForProfile[i].getOccurrences())
            {
                numOfMatchingStrs++;
            }
        }
        return (numOfMatchingStrs >= halfOfStrsForProfile);
    }

    /**
     * Uses a level-order traversal to populate an array of unmarked Strings representing unmarked people's names.
     * - USE the getMatchingProfileCount method to get the resulting array length.
     * - USE the provided Queue class to investigate a node and enqueue its
     * neighbors.
     * 
     * @return the array of unmarked people
     */
    public String[] getUnmarkedPeople() 
    {
        int numOfUnmarked = getMatchingProfileCount(false);
        String[] arrayOfUnmarked = new String[numOfUnmarked];
        Queue<TreeNode> queue = new Queue<>();
        queue.enqueue(treeRoot);
        int i = 0;

        while(!queue.isEmpty())
        {
            TreeNode currNode = queue.dequeue();
            if(currNode!=null)
            {
                if(!currNode.getProfile().getMarkedStatus())
                {
                    arrayOfUnmarked[i] = currNode.getName();
                    i++;
                }
            }
            if(currNode.getLeft()!=null)
            {
                queue.enqueue(currNode.getLeft());
            }
            if(currNode.getRight()!=null)
            {
                queue.enqueue(currNode.getRight());
            }
        }
        return arrayOfUnmarked; // update this line
    }

    /**
     * Removes a SINGLE node from the BST rooted at treeRoot, given a full name (Last, First)
     * This is similar to the BST delete we have seen in class.
     * 
     * If a profile containing fullName doesn't exist, do nothing.
     * You may assume that all names are distinct.
     * 
     * @param fullName the full name of the person to delete
     */
    public void removePerson(String fullName) {
        TreeNode last = null;
        TreeNode currNode = treeRoot;
        while(currNode!=null && !currNode.getName().equals(fullName))
        {
            last = currNode;
            if(fullName.compareTo(currNode.getName())<0)
            {
                currNode = currNode.getLeft();
            }
            else
            {
                currNode = currNode.getRight();
            }
        }
        if(currNode==null)
        {
            return;
        }

        if(currNode.getLeft()==null && currNode.getRight()==null)
        {
            if(last==null)
            {
                treeRoot=null;
            }
            else if(last.getLeft()==currNode)
            {
                last.setLeft(null);
            }
            else
            {
                last.setRight(null);
            }
        }
        else if(currNode.getLeft()!=null && currNode.getRight()!=null)
        {
            TreeNode nextParent = currNode;
            TreeNode next = currNode.getRight();
            while(next.getLeft()!=null)
            {
                nextParent = next;
                next = next.getLeft();
            }
            if(nextParent!=currNode)
            {
                nextParent.setLeft(next.getRight());
            }
            else
            {
                nextParent.setRight(next.getRight());
            }
            next.setLeft(currNode.getLeft());
            next.setRight(currNode.getRight());
            currNode.setName(next.getName());
            currNode.setProfile(next.getProfile());
            if(last==null)
            {
                treeRoot = next;
            }
        }
        else
        {
            TreeNode offspring = null;
            if(currNode.getLeft()!=null)
            {
                offspring = currNode.getLeft();
            }
            else
            {
                offspring = currNode.getRight();
            }
            if(last==null)
            {
                treeRoot = offspring;
            }
            else if(currNode==last.getLeft())
            {
                last.setLeft(offspring);
            }
            else
            {
                last.setRight(offspring);
            }
        }

    }

    /**
     * Clean up the tree by using previously written methods to remove unmarked
     * profiles.
     * Requires the use of getUnmarkedPeople and removePerson.
     */
    public void cleanupTree() 
    {
        String[] notOfInterest = getUnmarkedPeople();
        for(String fullname : notOfInterest)
        {
            removePerson(fullname);
        }
    }

    /**
     * Gets the root of the binary search tree.
     *
     * @return The root of the binary search tree.
     */
    public TreeNode getTreeRoot() {
        return treeRoot;
    }

    /**
     * Sets the root of the binary search tree.
     *
     * @param newRoot The new root of the binary search tree.
     */
    public void setTreeRoot(TreeNode newRoot) {
        treeRoot = newRoot;
    }

    /**
     * Gets the first unknown sequence.
     * 
     * @return the first unknown sequence.
     */
    public String getFirstUnknownSequence() {
        return firstUnknownSequence;
    }

    /**
     * Sets the first unknown sequence.
     * 
     * @param newFirst the value to set.
     */
    public void setFirstUnknownSequence(String newFirst) {
        firstUnknownSequence = newFirst;
    }

    /**
     * Gets the second unknown sequence.
     * 
     * @return the second unknown sequence.
     */
    public String getSecondUnknownSequence() {
        return secondUnknownSequence;
    }

    /**
     * Sets the second unknown sequence.
     * 
     * @param newSecond the value to set.
     */
    public void setSecondUnknownSequence(String newSecond) {
        secondUnknownSequence = newSecond;
    }

}
