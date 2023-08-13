import java.util.*;
class Message {
    //declare a private string field to store the message content
    private String content;
    //create a constructor that takes a string parameter and assigns it to the content field
    public Message(String content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String toString() {
        return this.content;
    }
}
//create a class for the input layer
class InputLayer {
    //declare a private queue field to store the messages
    private Queue<Message> queue;
    //create a constructor that initializes the queue field as a linked list
    public InputLayer() {
        this.queue = new LinkedList<>();
    }
    //create a method that takes a string parameter and creates a message object from it
    public Message createMessage(String input) {
        //check if the input is null or empty, and throw an exception if so
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        //check if the input length is more than 250 characters, and throw an exception if so
        if (input.length() > 250) {
            throw new IllegalArgumentException("Input cannot be more than 250 characters");
        }
        //create a message object with the input as the content
        Message message = new Message(input);
        //return the message object
        return message;
    }
    //create a method that takes a message object and adds it to the queue
    public void enqueueMessage(Message message) {
        //check if the message is null, and throw an exception if so
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        //add the message to the queue
        this.queue.add(message);
    }
    //create a method that returns and removes the first message from the queue
    public Message dequeueMessage() {
        //check if the queue is empty, and throw an exception if so
        if (this.queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        //return and remove the first message from the queue
        return this.queue.remove();
    }

    //create a method that returns but does not remove the first message from the queue
    public Message peekMessage() {
        //check if the queue is empty, and throw an exception if so
        if (this.queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        //return but do not remove the first message from the queue
        return this.queue.peek();
    }
}

//create a class for the processing layer
class ProcessingLayer {
    //declare a private stack field to store the messages
    private Stack<Message> stack;

    //declare a private arraylist field to store the messages that have been sent
    private ArrayList<Message> sentMessages;

    //create a constructor that initializes the stack and the arraylist fields
    public ProcessingLayer() {
        this.stack = new Stack<>();
        this.sentMessages = new ArrayList<>();
    }

    //create a method that takes a message object and pushes it to the stack
    public void pushMessage(Message message) {
        //check if the message is null, and throw an exception if so
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        //push the message to the stack
        this.stack.push(message);
    }

    //create a method that returns and pops the top message from the stack
    public Message popMessage() {
        //check if the stack is empty, and throw an exception if so
        if (this.stack.isEmpty()) {
            throw new EmptyStackException();
        }
        //return and pop the top message from the stack
        return this.stack.pop();
    }

    //create a method that returns but does not pop the top message from the stack
    public Message peekMessage() {
        //check if the stack is empty, and throw an exception if so
        if (this.stack.isEmpty()) {
            throw new EmptyStackException();
        }
        //return but do not pop the top message from the stack
        return this.stack.peek();
    }

    //create a method that prints the message content to the console and adds it to the sent messages list
    public void printMessage(Message message) {
        //check if the message is null, and throw an exception if so
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        //print the message content to the console
        System.out.println(message.getContent());
        //add the message to the sent messages list
        this.sentMessages.add(message);
    }
    //create a method that searches for messages that contain a given word and returns a list of matching messages
    public ArrayList<Message> searchMessages(String word) {
        //check if the word is null or empty, and throw an exception if so
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        //create an arraylist to store the matching messages
        ArrayList<Message> matches = new ArrayList<>();
        //loop through the sent messages list and check each message content for the word
        for (Message message : this.sentMessages) {
            //convert both the message content and the word to lower case for case-insensitive comparison
            String content = message.getContent().toLowerCase();
            String wordLower = word.toLowerCase();
            //if the message content contains the word, add it to the matches list
            if (content.contains(wordLower)) {
                matches.add(message);
            }
        }
        //return the matches list
        return matches;
    }
    //create a method that updates a message at a given index with a new content
    public void updateMessage(int index, String newContent) {
        //check if the index is valid, and throw an exception if not
        if (index < 0 || index >= this.sentMessages.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        //check if the new content is null or empty, and throw an exception if so
        if (newContent == null || newContent.isEmpty()) {
            throw new IllegalArgumentException("New content cannot be null or empty");
        }
        //check if the new content length is more than 250 characters, and throw an exception if so
        if (newContent.length() > 250) {
            throw new IllegalArgumentException("New content cannot be more than 250 characters");
        }
        //get the message at the given index and update its content with the new content
        Message message = this.sentMessages.get(index);
        message.setContent(newContent);
    }
    //create a method that deletes a message at a given index
    public void deleteMessage(int index) {
        //check if the index is valid, and throw an exception if not
        if (index < 0 || index >= this.sentMessages.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        //remove the message at the given index from the sent messages list
        this.sentMessages.remove(index);
    }

    //create a method that prints all the messages in the sent messages list along with their indices
    public void printAllMessages() {
        //loop through the sent messages list and print each message and its index
        for (int i = 0; i < this.sentMessages.size(); i++) {
            Message message = this.sentMessages.get(i);
            System.out.println(i + ": " + message);
        }
    }
}
class Main {
    public static void main(String[] args) {
        //create an input layer object
        InputLayer inputLayer = new InputLayer();
        //create a processing layer object
        ProcessingLayer processingLayer = new ProcessingLayer();
        Scanner scanner = new Scanner(System.in);
        //create a boolean variable to control the loop
        boolean running = true;
        while (running) {
            //display the menu options
            System.out.println("Please choose an option:");
            System.out.println("1. Create and send a message");
            System.out.println("2. Search for messages by word");
            System.out.println("3. Update a message by index");
            System.out.println("4. Delete a message by index");
            System.out.println("5. Print all messages");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter your message content (max 250 characters):");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    //create a message object from the user input using the input layer method
                    Message message = inputLayer.createMessage(input);
                    //enqueue the message to the input layer queue using the input layer method
                    inputLayer.enqueueMessage(message);
                    //dequeue the message from the input layer queue and push it to the processing layer stack using their methods
                    Message message2 = inputLayer.dequeueMessage();
                    processingLayer.pushMessage(message2);
                    //pop the message from the processing layer stack and print it to the console using their methods
                    Message message3 = processingLayer.popMessage();
                    processingLayer.printMessage(message3);
                    break;
                case 2:
                    //search for messages by word
                    System.out.println("Please enter a word to search for:");
                    scanner.nextLine();
                    String word = scanner.nextLine();
                    //search for messages that contain the word using the processing layer method and store the result in an arraylist
                    ArrayList<Message> results = processingLayer.searchMessages(word);
                    //check if the results list is empty, and print a message if so
                    if (results.isEmpty()) {
                        System.out.println("No messages found with the word " + word);
                    } else {
                        //otherwise, print the number of messages found and loop through the results list and print each message
                        System.out.println(results.size() + " messages found with the word " + word);
                        for (Message result : results) {
                            System.out.println(result);
                        }
                    }
                    break;
                case 3:
                    //update a message by index
                    System.out.println("Please enter an index to update:");
                    int index = scanner.nextInt();
                    System.out.println("Please enter a new content for the message (max 250 characters):");
                    scanner.nextLine();
                    String newContent = scanner.nextLine();
                    //try to update the message at the given index with the new content using the processing layer method
                    try {
                        processingLayer.updateMessage(index, newContent);
                        //print a success message if no exception is thrown
                        System.out.println("Message updated successfully");
                    } catch (Exception e) {
                        //print the exception message if an exception is thrown
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    //delete a message by index
                    System.out.println("Please enter an index to delete:");
                    int index2 = scanner.nextInt();
                    //try to delete the message at the given index using the processing layer method
                    try {
                        processingLayer.deleteMessage(index2);
                        //print a success message if no exception is thrown
                        System.out.println("Message deleted successfully");
                    } catch (Exception e) {
                        //print the exception message if an exception is thrown
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    //print all messages in the sent messages list using the processing layer method
                    processingLayer.printAllMessages();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Arrivederci");
    }
}