# Bible Lib

_Note_ This library is unfinished, so use it as a stepping-stone for your own application or wait until a more stable version is out.

## What is this?
A library for interacting with and parsing the data within the bible.

The purpose of this is to work with a large dataset, parse it, and compare data. My goal is to utilize this within a GUI application and for others to be able to do as they with the library.

*Note*
This utilizes the Luke Smith edited version of the KJV bible for testing purposes. In the future I plan for it to consume API data and built data structured in a similar way for offline consumption.

## Usage
Using your Clojure REPL you can test out some of the functions and see the results;

```clojure
;Example for returning the first verse in Genesis
(first (bible-lib.core/bible-data)) ;=> ["Genesis" "Ge" "1" "1" "1" "In the beginning God created the heaven and the earth."]
```

Here's another example for getting the frequency of words used in the bible
```clojure
(bible-lib.core/frequency-data (bible-lib.core/bible-data))
```
[View result](https://gist.github.com/MaterialFuture/906317ac3c9e41ad7da76457827175c3)

## License
TODO: Change license

Copyright © 2022 @MaterialFuture

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
