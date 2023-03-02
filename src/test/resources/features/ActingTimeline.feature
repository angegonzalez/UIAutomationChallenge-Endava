@Timeline
Feature: Acting timeline

  Scenario Outline: Validate acting timeline
    Given the user selects a "<movie>" with actors
    And the user selects an actor from the top billed cast
    When the user checks the actor's acting timeline
    Then the title of the movie should be in the timeline

    Examples:
      | movie                                                                 |
      | https://www.themoviedb.org/movie/315162-puss-in-boots-the-last-wish   |
      | https://www.themoviedb.org/movie/536554-m3gan                         |
      | https://www.themoviedb.org/movie/505642-black-panther-wakanda-forever |
      | https://www.themoviedb.org/movie/1077280-die-hart                     |