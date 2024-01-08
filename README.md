# Runway Simulation


## Table of Contents

- [Description](#description)
- [Sample Output](#author)
- [Usage](#usage)
- [Implementation Details](#implementation-details)
- [Author](#author)

## Description

The runway simulation involves the following components:

### Arrival and Departure Threads

- **Arrival Thread:** Instantiates new airplane objects and adds them to the arrival queue.
- **Departure Thread:** Instantiates new airplane objects and adds them to the departure queue.

### Runway Thread

- Manages the use of the runway by determining which airplane object has priority.
- Ensures that only one airplane object uses the runway at a time.

### Airplane Objects

- Have set arrival and departure times, representing the time each operation takes.
- Prioritization is given to arriving flights on the runway.

### Arrival and Departure Queues

- Implemented using a custom `Queue` interface.
- Exception classes `QueueEmptyException` and `QueueFullException` are provided for the `ArrayQueue` implementation.

### Randomized Time Intervals

- Airplane objects are added to queues at random intervals using the `timeTillNext` method.

## Sample Output

![Sample Output](runwaySimulation/runwaySimulation/sampleOutput.png "Runway Simulation Sample Output")

## Usage

To run the simulation, follow these steps:

1. Adjust the `time` variable in the `main` method to set the desired simulation duration in minutes.
2. Execute the program.

```java
public static void main(String[] args) { 
    int time = 2; // Set the simulation time in minutes
    Main myProgram = new Main();
    myProgram.startSimulation(time);
    // Additional code for handling final states and cleanup
}
```

## Implementation Details

### Custom Queue Interface

The simulation uses a custom `Queue` interface with the following methods:

- `enqueue(T d)`: Adds an item to the rear of the queue.
- `dequeue()`: Removes and returns the item from the front of the queue.
- `front()`: Returns the item at the front of the queue without removing it.
- `rear()`: Returns the item at the rear of the queue without removing it.
- `isEmpty()`: Returns true if the queue is empty, false otherwise.
- `isFull()`: Returns true if the queue is full, false otherwise.
- `getSize()`: Returns the current size of the queue.

### Arrival Thread

The `Arrival` class extends the `Thread` class and simulates arriving flights. Key points include:

- Utilizes the `ArrayQueue` class to manage a queue of `Airplane` objects waiting to arrive.
- Randomly generates arrival times using the `SimulationTime.timeTillNext` method.
- Simulates the arrival of airplanes, updating their fuel amount and handling fuel depletion scenarios.
- Implements the `run` method, which is called by the `start` method, to run the arrival simulation.

### Departure Thread

The `Departure` class, similar to `Arrival`, extends the `Thread` class and simulates departing flights. Highlights include:

- Utilizes the `ArrayQueue` class to manage a queue of `Airplane` objects waiting to depart.
- Randomly generates departure times using the `SimulationTime.timeTillNext` method.
- Simulates the departure of airplanes, without considering fuel depletion.
- Implements the `run` method to run the departure simulation.

### Runway Thread

The `Runway` class, again extending the `Thread` class, orchestrates the arrivals and departures on the runway. Key features include:

- Prioritizes arriving flights while allowing both arrivals and departures.
- Manages counts of planes that have arrived and departed.
- Uses references to the `Arrival` and `Departure` threads.
- Implements the `run` method to execute the runway simulation.

### SimulationTime

- `timeTillNext(int meanArrivalTime)`: Calculates the time until the next event occurs based on a mean arrival time. It uses a random Gaussian distribution to introduce variability in arrival times.
- `randomAirplaneID()`: Generates a randomly created ID for an airplane using a combination of a randomly selected airline code from the `AIRLINES` array and a random number.


### Exception Handling

- The simulation includes two custom exception classes: `QueueEmptyException` and `QueueFullException`. 
- These are used to handle exceptional cases when interacting with the queue.

## Author
- Brandon Kenney, 2023
