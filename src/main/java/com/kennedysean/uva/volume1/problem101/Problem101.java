package com.kennedysean.uva.volume1.problem101;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Problem 101
 * The blocks problem
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=37
 */
public class Problem101 {

    private static Splitter splitter = Splitter.on(' ').omitEmptyStrings().trimResults();
    private static List<Stack<Integer>> blockList;

    public static void main(String[] args) throws IOException {
        parseInputStream(System.in);
    }

    public static void parseInputStream(InputStream inputStream) throws IOException {
        blockList =  new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();

        // Create a List of Integer Stacks for the given number of blocks
        initBlockList(Integer.parseInt(line));

        // Read input line by line
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("quit")) {
                break;
            }
            parseLine(line);
        }

        // Send output to System.out and close the input stream
        inputStream.close();
        printBlockList();
    }

    private static void parseLine(final String line) {
        List<String> elements = splitter.splitToList(line);

        // We assume lines are in the form:
        // move|pile x onto|over y
        // Where x is the source block, and y is the target block
        runCommand(
                elements.get(0).equals("move"),
                Integer.parseInt(elements.get(1)),
                elements.get(2).equals("onto"),
                Integer.parseInt(elements.get(3))
        );
    }

    private static void runCommand(final boolean isMove,
                                   final int sourceBlock,
                                   final boolean isOnto,
                                   final int targetBlock) {

        // Check if move is legal otherwise ignore it
        if (isMoveLegal(sourceBlock, targetBlock)) {
            // Fetch the current indexes of the source and target blockList both within the list and the stack
            int sourceListIndex = getListIndex(sourceBlock);
            int sourceStackIndex = blockList.get(sourceListIndex).indexOf(sourceBlock);
            int targetListIndex = getListIndex(targetBlock);
            int targetStackIndex = blockList.get(targetListIndex).indexOf(targetBlock);

            if (isMove) {
                // Remove anything atop of the source block and move them to their initial position
                moveBlocks(sourceListIndex, sourceStackIndex, -1);

                if (isOnto) {
                    // If moving directly onto target block, remove anything atop of the source block
                    // and move them to their initial position
                    moveBlocks(targetListIndex, targetStackIndex, -1);
                }

                // Finally, move the source block to the target stack
                blockList.get(sourceListIndex).remove(sourceStackIndex);
                blockList.get(targetListIndex).push(sourceBlock);
            }
            else {
                if (isOnto) {
                    // If moving directly onto target block, remove anything atop of the source block
                    // and move them to their initial position
                    moveBlocks(targetListIndex, targetStackIndex, -1);
                }

                // Move the entire source pile inclusive of the source block and anything above it in the stack
                moveBlocks(sourceListIndex, sourceStackIndex - 1, targetListIndex);
            }
        }
    }

    private static void moveBlocks(final int sourceListIndex, final int stackIndex, final int targetListIndex) {
        Stack<Integer> stack = blockList.get(sourceListIndex);
        int cursorBlock;

        // While something exists in the stack above the current index
        while (stack.size() > stackIndex + 1) {
            // Get a cursor block and remove it from the stack
            cursorBlock = stack.get(stackIndex + 1);
            stack.remove(stack.get(stackIndex + 1));

            // Push it to targetIndex of new stack, where -1 means return it to the initial stack index
            int targetIndex = targetListIndex == -1 ? cursorBlock : targetListIndex;
            blockList.get(targetIndex).push(cursorBlock);
        }
    }

    private static boolean isMoveLegal(final int sourceBlock, final int targetBlock) {
        // Source and target can't be the same block, or be in the same stack
        if (sourceBlock == targetBlock || getListIndex(sourceBlock) == getListIndex(targetBlock)) {
            return false;
        }

        // Otherwise legal
        return true;
    }

    private static int getListIndex(int blockNumber) {
        int numberOfBlocks = blockList.size();
        for (int i = 0; i < numberOfBlocks; i++) {
            Stack<Integer> stack = blockList.get(i);
            if (stack.contains(blockNumber)) {
                return i;
            }
        }

        return -1;
    }

    private static void initBlockList(int numberOfBlocks) {
        for (int i = 0; i < numberOfBlocks; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            blockList.add(stack);
        }
    }

    private static void printBlockList() {
        int numberOfBlocks = blockList.size();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numberOfBlocks; i++) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(String.valueOf(i));
            stringBuilder.append(":");

            Stack<Integer> stack = blockList.get(i);
            for (Integer block : stack) {
                stringBuilder.append(" ");
                stringBuilder.append(String.valueOf(block));
            }
        }

        System.out.print(stringBuilder.toString());
    }
}