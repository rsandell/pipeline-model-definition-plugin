/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

pipeline {
  agent none
  stages {
    stage("foo") {
      matrix {
        axes {
          axis {
            name 'os'
            values "linux", "windows", "mac"
          }
          axis {
            name 'browser'
            values "firefox", "chrome", "safari", "ie"
          }
        }
        excludes {
          exclude {
            axis {
              name 'os'
              values 'linux'
            }
            axis {
              name 'browser'
              values 'safari', 'safari'
            }
          }
          exclude {
            axis {
              name 'os'
              notValues 'windows'
            }
            axis {
              name 'browser'
              values 'ie'
            }
          }
        }
        stages {
          stage("first") {
            steps {
              echo "First branch"
              echo "OS=$os"
              echo "BROWSER=$browser"
            }
          }
          stage("second") {
            steps {
              echo "Second branch"
            }
          }
        }
      }
    }
  }
}
