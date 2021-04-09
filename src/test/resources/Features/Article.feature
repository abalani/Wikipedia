Feature: Verify wikipedia article page content

  # Could be run as a single test by combining all the steps

  @smoke
  Scenario: Check content box headings are used as page headings
    Given user is on the article page
    Then user verifies content box headings match page headings

  @smoke
  Scenario: Check content list has functioning links
    Given user is on the article page
    Then user verifies content headings are functioning links

  @smoke
  Scenario: Check Nike pop-up has the expected content
    Given user is on the article page
    Then user verifies Nike has expected pop-up content

  @smoke
  Scenario: Check Nike page has family tree
    Given user is on the article page
    Then user clicks Nike under personified concepts
    Then user verifies Nike page has a family tree